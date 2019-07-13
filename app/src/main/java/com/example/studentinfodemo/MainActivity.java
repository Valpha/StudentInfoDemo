package com.example.studentinfodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.FloatBuffer;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_stunumber;
    private ListView lv_student;
    private List<Student> list;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StudentInfoUtils.setContext(this);
        initView();
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_stunumber = (EditText) findViewById(R.id.et_stunumber);
        lv_student = (ListView) findViewById(R.id.lv_student);
        adapter = new MyAdapter();
        lv_student.setAdapter(adapter);
    }

    public void insertStudent(View view) {
        String name = et_name.getText().toString().trim();
        String stunum = et_stunumber.getText().toString().trim();
        if (name.length() > 0 && stunum.length() > 0) {
            Student stu = new Student(0, name, Integer.valueOf(stunum));
            long count = StudentInfoUtils.insertStudent(stu);
            if (count <= 0) {
                Toast.makeText(this, "您没有添加学生", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "信息添加成功！目前一共添加了" + count + "条学生信息", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "信息不全！无法添加学生信息", Toast.LENGTH_SHORT).show();
        }
    }


    public void deleteStudent(View view) {
        String name = et_name.getText().toString().trim();
        String stunum = et_stunumber.getText().toString().trim();
        if (name.length() <= 0 && stunum.length() <= 0) {
            Toast.makeText(this, "信息错误！无法删除学生", Toast.LENGTH_SHORT).show();
        } else if (name.length() > 0 && stunum.length() <= 0) {
            //  按姓名删除
            int count = StudentInfoUtils.deleteStudent(name);
            if (count <= 0) {
                Toast.makeText(this, "没有您要删除学生", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "成功删除了" + count + "条学生信息", Toast.LENGTH_LONG).show();
            }
        } else if (name.length() <= 0 && stunum.length() > 0) {
            //  按学号删除
            int count = StudentInfoUtils.deleteStudent(Integer.valueOf(stunum));
            if (count <= 0) {
                Toast.makeText(this, "没有您要删除学生", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "成功删除了" + count + "条学生信息", Toast.LENGTH_LONG).show();
            }
        } else {
            //  按姓名+学号删除
            int count = StudentInfoUtils.deleteStudent(name, Integer.valueOf(stunum));
            if (count <= 0) {
                Toast.makeText(this, "没有您要删除学生", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "成功删除了" + count + "条学生信息", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void updateStudent(View view) {
        String name = et_name.getText().toString().trim();
        String stunum = et_stunumber.getText().toString().trim();
        if (stunum.length() <= 0) {
            Toast.makeText(this, "信息错误！无法修改学生", Toast.LENGTH_SHORT).show();
        } else {
            int count = StudentInfoUtils.updateStudent(Integer.valueOf(stunum), name);
            if (count <= 0) {
                Toast.makeText(this, "没有您要更新学生", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "成功更新了" + count + "条学生信息", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void queryStudent(View view) {
        String name = et_name.getText().toString().trim();
        String stunum = et_stunumber.getText().toString().trim();
        if (name.length() <= 0 && stunum.length() <= 0) {
            //  查询所有的学生信息
            list = StudentInfoUtils.queryStudent();
        } else if (name.length() > 0 && stunum.length() <= 0) {
            //  按姓名查询
            list = StudentInfoUtils.queryStudent(name);
        } else if (name.length() <= 0 && stunum.length() > 0) {
            //  按学号查询
            list = StudentInfoUtils.queryStudent(Integer.valueOf(stunum));
        } else {
            //  按姓名+学号查询
            list = StudentInfoUtils.queryStudent(name, Integer.valueOf(stunum));
        }
        adapter.notifyDataSetChanged();
    }

    private class MyAdapter extends BaseAdapter {
        private ViewHolder viewHolder;

        @Override
        public int getCount() {
            if (list != null) {
                return list.size();
            } else {
                return 0;
            }
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.student_item, null);
                viewHolder = new ViewHolder();
                viewHolder._id = convertView.findViewById(R.id._id);
                viewHolder.name = convertView.findViewById(R.id.name);
                viewHolder.stunumber = convertView.findViewById(R.id.stunumber);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Student stu = list.get(position);
            viewHolder._id.setText(String.valueOf(stu.get_id()));
            viewHolder.name.setText(stu.getName());
            viewHolder.stunumber.setText(String.valueOf(stu.getStunumber()));
            return convertView;
        }

        private class ViewHolder {
            public TextView _id;
            public TextView name;
            public TextView stunumber;
        }
    }
}
