// ==UserScript==
// @name         网盘共享文件库目录清单导出
// @version      0.1
// @description  try to take over the world!
// @author       radapp
// @match        https://pan.baidu.com/*
// @grant        none
// @require      https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/1.3.3/FileSaver.min.js
// @require      https://cdn.bootcdn.net/ajax/libs/jquery/3.1.1/jquery.min.js
// @note         暴力转存网盘目录
// ==/UserScript==

(function () {
    // 获取你某个群组的 listshare 接口数据
    let listshareUrl = 'https://pan.baidu.com/mbox/group/listshare?clienttype=0&app_id=250528&web=1&dp-logid=79869100702558280071&type=2&gid=1005082622835627599&limit=50&desc=1'
    // 提取出来 listshare 接口中的必要参数，下一个 shareinfo 接口会用
    let clienttype = '0'
    let web = '1'
    let type = '2'
    let app_id = '250528'
    let dp_logid = '79869100702558280071'
    let gid = '1005082622835627599'

    // 获取群文件分享列表
    // 第一级目录只取了 50 条，如果不够，你再改逻辑
    function getListShare() {
        $.ajax({
            type: 'GET',
            url: listshareUrl,
            data: {},
            dataType: "json",
            async: false, // 禁用异步
            success: function (res) { // 响应内容可能会变，根据实际情况调整
                console.log(res)
                if (res.errno !== 0) {
                    console.warn(res);
                    return;
                }
                // 分别递归获取第一级目录的子目录
                let msg_list = res.records.msg_list
                for (const msg of msg_list) {
                    let msg_id = msg.msg_id
                    let from_uk = msg.uk
                    // msg 对象中包含1个或多个file对象
                    let file_list = msg.file_list
                    for (const file_item of file_list) {
                        getShareInfo(msg_id, from_uk, file_item)
                    }
                }
            },
            error: function (err) {
                console.error(err);
            }
        });
    }

    // 阻塞睡眠一会，别请求太快，细水长流
    function sleep(time) {
        return new Promise((resolve) => setTimeout(resolve, time));
    }

    function saveToDB(item) {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/api/file',
            data: item,
            dataType: "json",
            async: true, // 自己的服务器，随便造
            success: function (res) {
                // 可选重试
            },
            error: function (err) {
                console.error(err);
                // 可选重试
            }
        });
    }

    // 递归获取所有子目录
    async function getShareInfo(msg_id, from_uk, file_item, parent_id) {
        // console.log(file_item)
        let fs_id = file_item.fs_id
        let is_dir = file_item.isdir
        let path = file_item.path
        let server_filename = file_item.server_filename
        let size = file_item.size
        let server_ctime = file_item.server_ctime
        let server_mtime = file_item.server_mtime
        console.log('开始获取 ' + server_filename + ' 相关资料')

        //
        let postItem = {
            fileId: fs_id,
            fileName: server_filename,
            dir: is_dir === 1,
            parentFileId: parent_id,
            path: path,
            size: size,
            createTime: server_ctime * 1000,
            updateTime: server_mtime * 1000
        }
        saveToDB(postItem)
        // 递归遍历目录
        if (is_dir) {
            console.log(fs_id, server_filename, 'is dir, start get sub items.')
            let page = 1
            let shareinfoUrl = 'https://pan.baidu.com/mbox/msg/shareinfo' +
                '?from_uk=' + from_uk +
                '&msg_id=' + msg_id +
                '&type=' + type +
                '&num=50' +
                '&page=' + page +
                '&fs_id=' + fs_id +
                '&gid=' + gid +
                '&limit=50' +
                '&desc=1' +
                '&clienttype=' + clienttype +
                '&app_id=' + app_id +
                '&web=' + web +
                '&dp-logid=' + dp_logid
            // 请求子级目录文件列表
            // 每次请求停顿 0.5s 避免封控
            console.log('sleep 500ms', new Date().getTime())
            await sleep(500);
            console.log('开始获取第' + page + '页', new Date().getTime())
            $.ajax({
                type: 'POST',
                url: shareinfoUrl,
                data: {},
                dataType: "json",
                async: false, // 同步请求，降低频次，避免封控，慢点比封掉好
                success: function (res) {
                    // console.log(res)
                    if (res.errno !== 0) {
                        console.warn(res);
                        return;
                    }
                    let file_list = []
                    res.records.filter(item => {
                        file_list.push(item)
                    })

                    async function getNext() {
                        // 判断是否有更多，每次请求50条记录，直到请求完全
                        let hasMore = res.has_more
                        while (hasMore) {
                            page++
                            shareinfoUrl = 'https://pan.baidu.com/mbox/msg/shareinfo' +
                                '?from_uk=' + from_uk +
                                '&msg_id=' + msg_id +
                                '&type=' + type +
                                '&num=50' +
                                '&page=' + page +
                                '&fs_id=' + fs_id +
                                '&gid=' + gid +
                                '&limit=50' +
                                '&desc=1' +
                                '&clienttype=' + clienttype +
                                '&app_id=' + app_id +
                                '&web=' + web +
                                '&dp-logid=' + dp_logid
                            // 每次请求停顿 0.5s 避免封控
                            console.log('sleep 500ms', new Date().getTime())
                            await sleep(500);
                            console.log('开始获取第' + page + '页', new Date().getTime())
                            $.ajax({
                                type: 'POST',
                                url: shareinfoUrl,
                                dataType: "json",
                                async: false, // 同步请求，降低频次，避免封控，慢点比封掉好
                                success: function (res2) {
                                    // console.log(res2)
                                    if (res2.errno !== 0) {
                                        console.warn(res2);
                                        return;
                                    }
                                    res2.records.filter(item => {
                                        file_list.push(item)
                                    })
                                    // 重新赋值 hasMore
                                    hasMore = res2.has_more
                                },
                                error: function (err) {
                                    console.error(err);
                                }
                            });
                        }
                    }

                    getNext()
                    console.log(fs_id, server_filename, '获取完毕,总计' + page + '页')
                    // console.log(file_list)
                    for (const sub_item of file_list) {
                        getShareInfo(msg_id, from_uk, sub_item, fs_id)
                    }
                },
                error: function (err) {
                    console.error(err);
                }
            });
        } else {
            console.log(fs_id, server_filename, 'is file, done.')
        }
    }

    // 添加导出按钮
    function addButton() {
        let myButton = $('<button type="button" class="u-button u-button--default u-button--mini" style="position: fixed;top: 58px;right: 48px;z-index: 9999;">导出目录</button>');
        $('body').append(myButton);
        myButton.click(getListShare);
    }

    addButton();


})()