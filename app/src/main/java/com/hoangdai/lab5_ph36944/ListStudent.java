package com.hoangdai.lab5_ph36944;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListStudent extends AppCompatActivity {
    Button btnadd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liststudent);


        ListView lvstudent = findViewById(R.id.lvstudent);

        ArrayList<Student> arrst = new ArrayList<>();
        arrst.add(new Student("Hà Nội", "Nguyễn Văn A", "Hà Nội"));
        arrst.add(new Student("Đà Nẵng", "Nguyễn Thị B", "Đà Nẵng"));
        arrst.add(new Student("Tây Nguyên", "Hoàng Đình C", "Tây Nguyên"));
        arrst.add(new Student("Hồ Chí Minh", "Nguyễn Đình D", "Vĩnh Long"));
        arrst.add(new Student("Cần Thơ", "Trương Thị H", "Huế"));

        StudentListViewAdapter studentListViewAdapter = new StudentListViewAdapter(this, arrst);
        lvstudent.setAdapter(studentListViewAdapter);


        ActivityResultLauncher<Intent> getData = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode()==2){
                            Intent intent=result.getData();//lấy về dữ liệu
                            Bundle bundle=intent.getExtras();//lấy về gói
                            String name = bundle.getString("name");
                            String address = bundle.getString("address");
                            String branch = bundle.getString("branch");
                            arrst.add(new Student(branch, name, address));
                            studentListViewAdapter.notifyDataSetChanged();
                        }
                    }
                }
        );

        //xử lý sự kiện
            btnadd = findViewById(R.id.btnadd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListStudent.this, AddStudent.class);
                getData.launch(intent);
            }
        });
    }
}
