package com.dell.sqliteapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    EditText editName,editSurname,editMarks,editId;
    Button addbtn,viewbtn,updatebtn,dltbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editId = (EditText)findViewById(R.id.editText);
        editName = (EditText)findViewById(R.id.editText2);
        editSurname = (EditText)findViewById(R.id.editText3);
        editMarks = (EditText)findViewById(R.id.editText4);
        addbtn = (Button)findViewById(R.id.button);
        viewbtn = (Button)findViewById(R.id.button2);
        updatebtn = (Button)findViewById(R.id.button3);
        dltbtn = (Button)findViewById(R.id.button4);

        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }


    //button listener for (addbtn) AddData..

    public  void AddData(){
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              boolean isInserted = myDb.insertData(editId.getText().toString(),editName.getText().toString(),
                        editSurname.getText().toString(),
                        editMarks.getText().toString());

              if(isInserted == true) {
                  Toast.makeText(MainActivity.this, "Data Is Inserted", Toast.LENGTH_LONG).show();
                  editId.setText("");
                  editName.setText("");
                  editSurname.setText("");
                  editMarks.setText("");
              }
              else {
                  Toast.makeText(MainActivity.this, "Data Is Not Inserted", Toast.LENGTH_LONG).show();
              }

            }
        });
    }

    //button listener for (viewbtn) viewData..

    public void viewAll(){
        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Cursor res = myDb.getAllData();
               if(res.getCount() == 0){
                   // show message
                   showMessage ("Error","No Data Found");

                   return;
               }

               StringBuffer buffer = new StringBuffer();
               while (res.moveToNext()){

                   buffer.append("ID: "+res.getString(0)+"\n");
                   buffer.append("NAME: "+res.getString(1)+"\n");
                   buffer.append("SURNAME: "+res.getString(2)+"\n");
                   buffer.append("MARKS: "+res.getString(3)+"\n\n");

               }

               //show all data
                showMessage("Data",buffer.toString());


            }
        });
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    //button listener for (updatebtn) UpdateDAta..

    public void UpdateData(){
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated = myDb.updateData(editId.getText().toString(),editName.getText().toString(),
                        editSurname.getText().toString(),editMarks.getText().toString());

                if(isUpdated == true) {
                    Toast.makeText(MainActivity.this, "Data Is Updated", Toast.LENGTH_LONG).show();
                    editId.setText("");
                    editName.setText("");
                    editSurname.setText("");
                    editMarks.setText("");
                }
                else {
                    Toast.makeText(MainActivity.this, "Data Is Not Updated", Toast.LENGTH_LONG).show();
                }




            }
        });
    }

    //button listener for (dltbtn) DeleteData..

    public void DeleteData(){

        dltbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deleteRows = myDb.deleteData(editId.getText().toString());

                if(deleteRows > 0 ) {
                    Toast.makeText(MainActivity.this, "Data Is Deleted", Toast.LENGTH_LONG).show();
                    editId.setText("");
                }
                else {
                    Toast.makeText(MainActivity.this, "Data Is Not Deleted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
