package utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import domain.StudentInfo;

/**
 * Created by Fang on 2017/7/5.
 */

public class GetStudentInfo {

    /**

     * 解析Json数据

     *

     * @param urlPath

     * @return mlists

     * @throws Exception

     */

    public static StudentInfo getStudentInfo(String urlPath) throws Exception {


        byte[] data = readParse(urlPath);

        JSONArray array = new JSONArray(new String(data));
        StudentInfo student = new StudentInfo();

        for (int i = 0; i < array.length(); i++) {

            JSONObject item = array.getJSONObject(i);
            String name = item.getString("name");
            String sex = item.getString("sex");
            String deptname = item.getString("deptname");
            String specname = item.getString("specname");
            String classname = item.getString("classname");
            String nation = item.getString("nation");
            String idcard = item.getString("idcard");
            String admissionadate = item.getString("admissionadate");
            String studylength = item.getString("studylength");
            String birthday = item.getString("birthday");
            int grade = item.getInt("grade");

            student.setAdmission_date(admissionadate);
            student.setSex(sex);
            student.setSpec_name(specname);
            student.setDept_name(deptname);
            student.setClass_name(classname);
            student.setNation(nation);
            student.setId_card(idcard);
            student.setStudy_length(studylength);
            student.setGrade(grade);
            student.setBirthday(birthday);
            student.setStudent_name(name);


        }

        return student;

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