package com.c2b.c2baiagent.demo.invoke;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;

/**
 * ClassName: HttpAiInvoke
 * Package: com.c2b.c2baiagent.demo.invoke
 * Description:HTTP调用实例
 *
 * @Author lzy
 */
public class HttpAiInvoke {

    // 1. 替换为你的实际 API Key
    private static final String API_KEY = TestApiKey.API_KEY;

    // 2. 定义请求 URL
    private static final String URL = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";

    public static void main(String[] args) {
        // 构建 messages 数组
        JSONArray messages = JSONUtil.createArray();
        messages.add(JSONUtil.createObj()
                .set("role", "system")
                .set("content", "你是武志红"));
        messages.add(JSONUtil.createObj()
                .set("role", "user")
                .set("content", "三十而立"));

        // 3. 构建符合 DashScope 要求的 JSON 请求体
        String jsonBody = JSONUtil.createObj()
                .set("model", "qwen-plus")
                .set("input", JSONUtil.createObj()
                        .set("messages", messages) // 直接传入 JSONArray
                )
                .set("parameters", JSONUtil.createObj()
                        .set("result_format", "message")
                )
                .toString();

        // 4. 发起 POST 请求
        try (HttpResponse response = HttpRequest.post(URL)
                .header("Authorization", "Bearer " + API_KEY)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .timeout(30000)
                .execute()) {

            // 5. 打印响应结果
            System.out.println("响应状态码: " + response.getStatus());
            System.out.println("响应内容: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}