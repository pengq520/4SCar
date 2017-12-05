package com.example.ios27.a4scar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ios27.a4scar.dao.maintainDAO;
import com.example.ios27.a4scar.model.Tb_maintain;
import com.example.ios27.a4scar.model.tb_service;

import java.util.Calendar;

/**
 * Created by ios25 on 2017/11/30.
 */

public class AddMaintenance extends AppCompatActivity {
    protected static final int DATE_DIALOG_ID = 0;
    EditText txtCarID,txtTime,txtUser,txtUservice,txtPhone,txtType,txtMoney,txtMark;
    Button btnInSaveButton;
    Button btnInCancelButton;
    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmaintenance);
        txtCarID = (EditText)findViewById(R.id.txtCarID);
        txtTime = (EditText)findViewById(R.id.txtTime);
        txtUser = (EditText)findViewById(R.id.txtUser);
        txtUservice = (EditText)findViewById(R.id.txtUservice);
        txtPhone = (EditText)findViewById(R.id.txtPhone);
        txtType = (EditText)findViewById(R.id.txtType);
        txtMoney = (EditText)findViewById(R.id.txtMoney);
        txtMark = (EditText)findViewById(R.id.txtMark);
        btnInSaveButton = (Button)findViewById(R.id.btnInSaveButton);
        btnInCancelButton = (Button)findViewById(R.id.btnInCancelButton);

        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        updateDisplay();
        btnInSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strMoney = txtMoney.getText().toString();
                if(!strMoney.isEmpty()){
                    maintainDAO maintainDAO = new maintainDAO(AddMaintenance.this);
                    Tb_maintain tb_maintain = new Tb_maintain(maintainDAO.getMaxId()+1,
                            txtCarID.getText().toString(),txtTime.getText().toString(),
                            txtUser.getText().toString(),txtUservice.getText().toString(),
                            txtPhone.getText().toString(),txtType.getText().toString(),
                            Double.parseDouble(strMoney),txtMark.getText().toString());
                    maintainDAO.add(tb_maintain);
                    Toast.makeText(AddMaintenance.this,"【新增保养】数据添加成功！",Toast.LENGTH_SHORT).show();;
                }
                else {
                    Toast.makeText(AddMaintenance.this,"请输入保养金额！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnInCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtCarID.setText("");
                txtTime.setText("");
                txtTime.setHint("2012-12-12");
                txtUser.setText("");
                txtUservice.setText("");
                txtPhone.setText("");
                txtType.setText("");
                txtMoney.setText("");
                txtMoney.setHint("0.00");
                txtMark.setText("");
            }
        });

    }
    private void updateDisplay() {
        txtTime.setText(new StringBuilder().append(mYear).append("-").append
                (mMonth + 1).append("-").append(mDay));
    }//显示设置的时间

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,mDateSetListener,mYear,mMonth,mDay);
        }
        return super.onCreateDialog(id);
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            mYear = year;
            mMonth =monthOfYear;
            mDay = dayOfMonth;
            updateDisplay();
        }
    };
}
