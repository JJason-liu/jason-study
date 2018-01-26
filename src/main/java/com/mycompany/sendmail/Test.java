/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sendmail;

import com.mycompany.sendmail.MailManager;

/**
 *
 * @author 有泪的北极星 qq:765798166
 * @date 2018-1-22 17:58:52
 */
public class Test {

    public static void main(String[] args) throws Exception {
        MailManager.getInstance().sendMail("765798166@qq.com", "金花日志错误信息", "房间解散错误", "126行");
    }
}
