package com.bitc.project_kyj.utils;

import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

public class JSFunction {

    public static void alertLocation(String msg, String url, HttpServletResponse res) {
        try {
            res.setContentType("text/html;charset=UTF-8");
            PrintWriter out = res.getWriter();

            String script = "<script>";
            script += "alert('" + msg + "');";
            script += "location.href = '" + url + "';";
            script += "</script>";

            out.print(script);

        } catch (Exception e) {

        }

    }

    public static void alertBack(String msg, HttpServletResponse res) {
        try {
            res.setContentType("text/html;charset=UTF-8");
            PrintWriter out = res.getWriter();

            String script = "<script>";
            script += "alert('" + msg + "');";
            script += "history.back();";
            script += "</script>";

            out.print(script);

        } catch (Exception e) {

        }

    }


}
