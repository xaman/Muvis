/*
 * Copyright 2017 Martin Chamarro (@martinchamarro)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.martinchamarro.muvis.globalutils.logger;


import com.martinchamarro.muvis.Config;

public class Logger {

    private static final int MESSAGE_LIMIT = 3 * 1024;

    public static void i(String msg) {
        i(null, msg);
    }

    public static void i(String tag, String msg) {
        if (Config.DEBUG) android.util.Log.i(Config.APP_NAME, getText(tag, msg));
    }

    public static void e(String msg) {
        e(null, msg);
    }

    public static void e(String tag, String msg) {
        android.util.Log.e(Config.APP_NAME, getText(tag, msg));
    }

    public static void e(String tag, String msg, Throwable t) {
        android.util.Log.e(Config.APP_NAME, getText(tag, msg), t);
    }

    public static void d(String msg) {
        d(null, msg);
    }

    public static void d(String tag, String msg) {
        if (Config.DEBUG) android.util.Log.d(Config.APP_NAME, getText(tag, msg));
    }

    public static void v(String msg) {
        v(null, msg);
    }

    public static void v(String tag, String msg) {
        if (Config.DEBUG) android.util.Log.v(Config.APP_NAME, getText(tag, msg));
    }

    public static void w(String msg) {
        w(null, msg);
    }

    public static void w(String tag, String msg) {
        if (Config.DEBUG) android.util.Log.w(Config.APP_NAME, getText(tag, msg));
    }

    public static void wtf(String msg) {
        wtf(null, msg);
    }

    public static void wtf(String tag, String msg) {
        if (Config.DEBUG) android.util.Log.wtf(Config.APP_NAME, getText(tag, msg));
    }

    public static void l(String message) {
        l(null, message);
    }

    public static void l(String tag, String msg) {
        if (!Config.DEBUG) return;
        while (!empty(msg) && msg.length() > MESSAGE_LIMIT) {
            d(tag, msg.substring(0, MESSAGE_LIMIT));
            msg = msg.substring(MESSAGE_LIMIT);
        }
        d(tag, msg);
    }

    private static String getText(String tag, String msg) {
        return empty(tag) ? msg + "" : "[" + tag + "]" + msg;
    }

    private static boolean empty(String text) {
        return text == null || text.isEmpty();
    }

}