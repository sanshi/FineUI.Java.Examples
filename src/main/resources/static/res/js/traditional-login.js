(function() {
    var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    var rowUser = document.getElementById('row-user');
    var rowPwd = document.getElementById('row-pwd');
    var tbxUserName = document.getElementById('tbxUserName');
    var tbxPassword = document.getElementById('tbxPassword');
    var msg = document.getElementById('msg');

    function showMsg(ok, text) {
        msg.textContent = text;
        msg.className = 'msg show ' + (ok ? 'ok' : 'err');
    }

    // 手写必填校验（对应 FineUI.Java 的 required="true"）
    function validate() {
        var ok = true;
        if (!tbxUserName.value) {
            rowUser.classList.add('is-error');
            ok = false;
        } else {
            rowUser.classList.remove('is-error');
        }
        if (!tbxPassword.value) {
            rowPwd.classList.add('is-error');
            ok = false;
        } else {
            rowPwd.classList.remove('is-error');
        }
        return ok;
    }

    document.getElementById('btnLogin').addEventListener('click', function() {
        msg.className = 'msg';
        if (!validate()) {
            return;
        }

        // 手动带上 CSRF 令牌
        var headers = {
            'Content-Type': 'application/json'
        };
        headers[csrfHeader] = csrfToken;

        // 手动拼请求、发 AJAX（JSON 键名必须和后端 LoginForm 字段一字不差）
        fetch('/traditional/login', {
                method: 'POST',
                headers: headers,
                body: JSON.stringify({
                    userName: tbxUserName.value,
                    password: tbxPassword.value
                })
            })
            .then(function(resp) {
                return resp.json();
            })
            .then(function(result) {
                // 手动把结果更新回界面
                showMsg(result.success, result.success ? '成功登录！' : '用户名或密码错误！');
            })
            .catch(function() {
                showMsg(false, '请求失败');
            });
    });

    document.getElementById('btnReset').addEventListener('click', function() {
        tbxUserName.value = '';
        tbxPassword.value = '';
        rowUser.classList.remove('is-error');
        rowPwd.classList.remove('is-error');
        msg.className = 'msg';
    });
})();
