package com.navyliu.widget.unit5Lsn2Sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.navyliu.widget.R;

public class SqliteActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();

    private AppCompatEditText idEdit, nicknameEdit;
    private AppCompatEditText phoneEdit;

    DBOpenHelper dbOpenHelper;
    int version = 1;
    SQLiteDatabase database;
    SQLiteDatabase readDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        findViewId();
    }

    // 初始化控件
    private void findViewId() {
        idEdit = (AppCompatEditText) this.findViewById(R.id.edit_id);
        nicknameEdit = (AppCompatEditText) this.findViewById(R.id.edit_nickname);
        phoneEdit = (AppCompatEditText) this.findViewById(R.id.edit_phone);
    }


    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_create: // 创建数据库
                createDatabase();
                break;
            case R.id.btn_alter: // 升级数据库
                alertDatabase();
                break;
            case R.id.btn_insert: // 插入数据
                insertDatabase();
                break;
            case R.id.btn_delete: // 删除数据库中的数据
                deleteDatabase();
                break;
            case R.id.btn_update: // 更新数据库中的数据
                updateDatabase();
                break;
            case R.id.btn_query: // 查询好友信息
                queryDatabase();
                break;
            case R.id.btn_close: // 关闭数据库
                closeDatabase();
                break;
            case R.id.btn_delete_database: // 删除数据库
                deleteData();
                break;
        }
    }


    /**
     * 关闭数据库
     */
    private void closeDatabase() {
        readDatabase.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 删除数据库
     */
    private void deleteData() {
        // 删除名为friend的数据库
        deleteDatabase("friend.db");
        Toast.makeText(this, "数据库删除成功", Toast.LENGTH_LONG).show();
    }

    /**
     * 查询好友信息
     */
    private void queryDatabase() {
        // 可以使用query() 或 rawQuery() 来查询
//        dbOpenHelper = new DBOpenHelper(this,)
        readDatabase = dbOpenHelper.getReadableDatabase();

        Cursor cursor = readDatabase.rawQuery(
                "SELECT name, id FROM friend WHERE name LIKE ?"
                , new String[]{"%三%"});
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
                      String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            Log.d(TAG, "queryDatabase: ======rawQuery====id:" + id + ",name:" + name + ",phone:" + phone);
        }
        cursor.close();


//        //Cursor对象常用方法如下：
//        c.move(int offset); //以当前位置为参考,移动到指定行
//        c.moveToFirst();    //移动到第一行
//        c.moveToLast();     //移动到最后一行
//        c.moveToPosition(int position); //移动到指定行
//        c.moveToPrevious(); //移动到前一行
//        c.moveToNext();     //移动到下一行
//        c.isFirst();        //是否指向第一条
//        c.isLast();     //是否指向最后一条
//        c.isBeforeFirst();  //是否指向第一条之前
//        c.isAfterLast();    //是否指向最后一条之后
//        c.isNull(int columnIndex);  //指定列是否为空(列基数为0)
//        c.isClosed();       //游标是否已关闭
//        c.getCount();       //总数据项数
//        c.getPosition();    //返回当前游标所指向的行数
//        c.getColumnIndex(String columnName);//返回某列名对应的列索引值
//        c.getString(int columnIndex);   //返回当前行指定列的值
//        readDatabase.beginTransaction();


        // 第一个参数String：表名
        // 第二个参数String[]:要查询的列名
        // 第三个参数String：查询条件
        // 第四个参数String[]：查询条件的参数
        // 第五个参数String:对查询的结果进行分组
        // 第六个参数String：对分组的结果进行限制
        // 第七个参数String：对查询的结果进行排序
//        readDatabase.beginTransaction();
//        Cursor cursor = readDatabase.query("friend"
//                , null
//                , "name = '?'"
//                , new String[]{"张三李四六"}
//                , null, null, null);
//        readDatabase.setTransactionSuccessful();
//        readDatabase.endTransaction();
//        while (cursor.moveToNext()) {
//            int id = cursor.getInt(cursor.getColumnIndex("id"));
//            String name = cursor.getString(cursor.getColumnIndex("name"));
//            String phone = cursor.getString(cursor.getColumnIndex("phone"));
//            Log.d(TAG, "queryDatabase: ======rawQuery====id:" + id + ",name:" + name + ",phone:" + phone);
//        }
//
//        cursor.close();
    }

    /**
     * 删除数据库中的数据
     */
    private void deleteDatabase() {
        int id = Integer.parseInt(idEdit.getText().toString());
        // 使用delete方法删除表中的数据
        // 第一个参数String：需要操作的表名
        // 第二个参数String：where选择语句, 选择哪些行要被删除, 如果为null, 就删除所有行;
        // 第三个参数String[]： where语句的参数, 逐个替换where语句中的 "?" 占位符;
        database.delete("friend", "id=?", new String[]{id + ""});

        // 或者使用sql语句删除
//        id += 999999;
//        String sql = "DELETE FROM friend WHERE id = '" + id + "'";
//        database.execSQL(sql);

        Toast.makeText(this, "数据删除成功", Toast.LENGTH_LONG).show();
    }


    /**
     * 更新数据库
     */
    private void updateDatabase() {
        int id = Integer.parseInt(idEdit.getText().toString());
        String nickname = nicknameEdit.getText().toString();
        String phone = phoneEdit.getText().toString();

        // 创建一个ContentValues对象
        ContentValues values = new ContentValues();
        values.put("name", nickname);
        if (version == 2) {
            values.put("phone", phone);
        }
//        SQLiteDatabase writableDatabase = dbOpenHelper.getWritableDatabase();
//        SQLiteDatabase readableDatabase = dbOpenHelper.getReadableDatabase();

        // 调用update方法修改数据库
        // 第一个参数  String：表名
        // 第二个参数  ContentValues：ContentValues对象（需要修改的）
        // 第三个参数  String：WHERE表达式，where选择语句, 选择那些行进行数据的更新, 如果该参数为 null, 就会修改所有行;？号是占位符
        // 第四个参数  String[]：where选择语句的参数, 逐个替换 whereClause 中的占位符;
        database.update("friend", values, "id=?"
                , new String[]{id + ""});

        // 或者写成sql语句，用execSQL来执行
        id += 999999;
        String sql = "UPDATE friend " +
                "SET name = '" + nickname + "' " +
                "WHERE id = '" + id + "'";
        if (version == 2) {
            sql = "UPDATE friend " +
                    "SET name = '" + nickname + "_sql', phone = '" + phone + "' " +
                    "WHERE id = '" + id + "'";
        }
        database.execSQL(sql);
        Toast.makeText(this, "数据库数据更新成功", Toast.LENGTH_LONG).show();
    }


    /**
     * 插入数据
     */
    private void insertDatabase() {
        int id = Integer.parseInt(idEdit.getText().toString());
        String nickname = nicknameEdit.getText().toString();
        String phone = phoneEdit.getText().toString();

        // 获取sqlite的版本号
        int version = database.getVersion();

        /**
         * 插入数据 开始
         * 1、创建一个ContentValues对象values；
         * 2、给values中put键值对;
         * 3、通过insert插入数据，或者execSql插入数据。
         */
        ContentValues values = new ContentValues();
        // 插入的键值对，其中key对应的就是数据库表中的字段名，value对应的是表中要插入的值
        // PS：ContentValues的内部实现是使用的HashMap，但ContentValues相对来说有比较特殊，
        //      key只能是String类型，value只能是基本类型，不能是对象
        switch (this.version) {
            case 2:
                values.put("phone", phone);
            case 1:
                values.put("id", id);
                values.put("name", nickname);
                break;
        }

        // 第一个参数  table名称
        // 第二个参数  SQL不允许一个空列，如果ContentValues是空的
        //      ，那么这一列将被明确指明的值
        // 第三个参数  ContentValues对象
        database.insert("friend", null, values);
        // 或者写成sql语句，用execSQL来执行
        id += 999999;
        String sql = "INSERT INTO friend(id, name) " +
                "VALUES(" + id + ", '" + nickname + "')";
        if (this.version == 2) {
            sql = "INSERT INTO friend(id, name, phone) " +
                    "VALUES(" + id + ", '" + nickname + "', '" + phone + "')";
        }
        database.execSQL(sql);
        Toast.makeText(this, "数据库数据插入成功", Toast.LENGTH_LONG).show();
    }


    /**
     * 升级数据库
     */
    private void alertDatabase() {
        dbOpenHelper = new DBOpenHelper(this, "friend.db"
                , null, 2);
        // 只有调用了getWritableDatabase()或者getReadableDatabase()，数据库才会创建
        // SQLiteDatabase readableDatabase = dbOpenHelper.getReadableDatabase();
        database = dbOpenHelper.getWritableDatabase();
        readDatabase = dbOpenHelper.getReadableDatabase();

        // 保存当前的数据库版本号
        SharedPreferences sp = this.getSharedPreferences("database_version", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("version", 2);
        editor.commit();
        version = 2;
        Toast.makeText(this, "数据库升级成功"
                , Toast.LENGTH_LONG).show();
    }

    /**
     * 创建数据库
     */
    private void createDatabase() {
        // 获取sharepreferences中的版本包
        SharedPreferences sp = this.getSharedPreferences("database_version", MODE_PRIVATE);
        version = sp.getInt("version", 1);
//        Log.d(TAG, "createDatabase: ========version===" + version);
//        version = 2;

        // 创建DBOpenHelper对象，只执行这一句是不会创建数据库的
        dbOpenHelper = new DBOpenHelper(this,
                "friend.db", null, version);
        // 只有调用了getWritableDatabase()或者getReadableDatabase()，数据库才会创建
        // SQLiteDatabase readableDatabase = dbOpenHelper.getReadableDatabase();
        database = dbOpenHelper.getWritableDatabase();
        readDatabase = dbOpenHelper.getReadableDatabase();

        Toast.makeText(this, "数据库创建成功", Toast.LENGTH_LONG).show();
    }
}