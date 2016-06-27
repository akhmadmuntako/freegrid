package com.example.libprint;

/**
 * Created by gembong on 6/13/16.
 */

import android.content.Context;

import rego.printlib.export.regoPrinter;

public class UsbPrint {
    private regoPrinter printer;
    private int myState = 0;
    private String printName="RG-MLP80A(ESC)";

    private preDefiniation.TransferMode printmode = preDefiniation.TransferMode.TM_NONE;
    private boolean labelmark = true;

    public regoPrinter getObject() {
        return printer;
    }

    public void setObject(Context context) {
        printer = new regoPrinter(context);
    }

    public String getName() {
        return printName;
    }

    public void setName(String name) {
        printName = name;
    }
    public void setState(int state) {
        myState = state;
    }

    public int getState() {
        return myState;
    }

    public void connect(){
        String[] printInterface = getObject().CON_GetSupportPageMode();
        int state = getObject().CON_ConnectDevices(printName, "usb", 200);
        if (state > 0) {
//            Toast.makeText(ConnectAvtivity.this, R.string.mes_consuccess,
//                    Toast.LENGTH_SHORT).show();
//            mBconnect = true;
//            con.setText(R.string.TextView_close);// "¹Ø±Õ"
//            Intent intent = new Intent(ConnectAvtivity.this,
//                    PrintModeActivity.class);
            setState(state);
            //context.setName(PrintName);
            setPrintway(0);
        } else {
//            Toast.makeText(ConnectAvtivity.this, R.string.mes_confail,
//                    Toast.LENGTH_SHORT).show();
//            mBconnect = false;
//            con.setText(R.string.button_btcon);// "Á¬œÓ"
        }
    }

    public void execute(byte[] data, int count){
        getObject().ASCII_PrintBuffer(getState(), data, count);
    }

    public void setPrintway(int printway) {

        switch (printway) {
            case 0:
                printmode = preDefiniation.TransferMode.TM_NONE; //not using printer server
                break;
            case 1:
                printmode = preDefiniation.TransferMode.TM_DT_V1;
                break;
            default:
                printmode = preDefiniation.TransferMode.TM_DT_V2;
                break;
        }

    }

    public int getPrintway() {
        return printmode.getValue();
    }

    public boolean getlabel() {
        return labelmark;
    }

    public void setlabel(boolean labelprint) {
        labelmark = labelprint;
    }
}
