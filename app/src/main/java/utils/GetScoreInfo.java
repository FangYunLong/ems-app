package utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import domain.ScoreInfo;

/**
 * Created by Fang on 2017/7/6.
 */

public class GetScoreInfo {
    /**

     * 解析Json数据

     *

     * @param urlPath

     * @return mlists

     * @throws Exception

     */

    public static List<ScoreInfo> getScoreInfo(String urlPath) throws Exception {


        byte[] data = readParse(urlPath);

        JSONArray array = new JSONArray(new String(data));

        List<ScoreInfo> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            ScoreInfo scoreInfo = new ScoreInfo();
            JSONObject item = array.getJSONObject(i);
            String coursename = item.getString("coursename");
            String score = item.getString("score");

            scoreInfo.setCour_name(coursename);
            scoreInfo.setScore(score);

            list.add(scoreInfo);
        }

        return list;

    }

    /**

     * 从指定的url中获取字节数组

     *

     * @param urlPath

     * @return 字节数组

     * @throws Exception

     */

    public static byte[] readParse(String urlPath) throws Exception {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        byte[] data = new byte[1024];

        int len = 0;

        URL url = new URL(urlPath);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        InputStream inStream = conn.getInputStream();

        while ((len = inStream.read(data)) != -1) {

            outStream.write(data, 0, len);

        }

        inStream.close();

        return outStream.toByteArray();

    }
}
