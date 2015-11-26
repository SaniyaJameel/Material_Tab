package info.androidhive.materialtabs.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.net.URI;

import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.activity.contactAdapter;


public class OneFragment extends Fragment{

    public OneFragment() {
        // Required empty public constructor
    }

    @Nullable

    Uri contactInfo;
    URI contactinfo;
    Context c;
    info.androidhive.materialtabs.activity.soundex s;
    static  String []phone;
    static String []name;
    static String []soundex;
    static int []type;
    TableLayout tl;
    TableRow tr;
    TextView nameView;
    TextView phoneView;
    int Contactscount=0;
    int count=0;
    Cursor cursor;
    Button searchBtn;
    View.OnClickListener listener;
    EditText edit;
    android.support.v7.app.AlertDialog alert;
    ImageView img;
    ScrollView sc;
    ListView l;

    public void onAttach(Activity activity){
        super.onAttach(activity);

        cursor = activity.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        c=activity;


    }
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        c=getActivity();
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_one, container, false);










        ReadPhoneContacts(getActivity());
        Display();
         l=new ListView(c);
        l=(ListView)view.findViewById(R.id.list);
      //  String[]  items={"mehmood","ahed"};

        //ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(c , android.R.layout.simple_list_item_2,items);
        // l.setAdapter(arrayAdapter);
        contactAdapter cont=new contactAdapter(c,R.layout.contact_list,name,phone);
        l.setAdapter(cont);



        edit=(EditText)view.findViewById(R.id.editText);
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Log.e("string", name[4]);
                Log.e("string", "sads");
                // switch(v.getId()) {
                //  case R.id.new_btn:
                Log.e("string", name[4]);
                EditText e = (EditText) getActivity().findViewById(R.id.editText);
                String sub = e.getText().toString();

                String [] n=new String[Contactscount];
                String [] p=new String[Contactscount];

                if (sub.length() > 1) {

                    info.androidhive.materialtabs.activity.soundex s1;
                    s1 = new info.androidhive.materialtabs.activity.soundex();
                    String sx = s1.soundex(sub);

int index=0;
                    String[] n1;
                    String[] p1;
                    int cou=0;
                   // tl.removeAllViews();
                    for (int i = 0; i < Contactscount; i++) {

                        if (soundex[i] != "" && soundex[i] != null) {


                            if (soundex[i].contains(sx)) {
                               n[index]=name[i];
                                p[index++]=phone[i];



                                 for(int k=0;k<Contactscount;k++){
                                     if(n[k]!=null)
                                   cou++;

                                 }

int in=0;
                                n1=new String[cou];
                                p1=new String[cou];
                                for(int l=0;l<Contactscount;l++){

                                    n1[in]=n[l];
                                    p1[in++]=p[l];

                                }




                                contactAdapter cont=new contactAdapter(c,R.layout.contact_list,n1,p1);
                                l.setAdapter(cont);






                            }
                        }

                    }
                } else {
                 //   tl.removeAllViews();

                    contactAdapter cont=new contactAdapter(c,R.layout.contact_list,name,phone);
                    l.setAdapter(cont);

                    AddData();
                }

                Log.e("string", name[4]);
                //   break;
                //  }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //removeSpaces();
        // Log.e(name[1], "contact name--!!!");


     //   AddData();
        return view;

    }




    public void onClick(View v) {

    }





    public void search(View v){

    }

    public void AddData()
    {



    }


    public void Display(){

        int c=48;

        for(int i=0 ; i <= c  ; i++){

            Log.e(name[i], "contact name");

            Log.println(type[i], "phone type", "phone type");

            Log.e(phone[i], "contact number");

        }

    }

    public void update(View v) {
        // Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        // intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        // startActivityForResult(intent, 1);
        AddData();
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        startActivityForResult(intent, 1);
        // ContentResolver cr = getContentResolver();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        // super.onActivityResult(requestCode,resultCode,data);
        // PhoneContacts phoneContacts = new PhoneContacts(service);
        // phoneContacts.ReadPhoneContacts(cntx);

        // if(requestCode==1){contactInfo=data.getData();}


    }


    public void ReadPhoneContacts(Context cntx) //This Context parameter is nothing but your Activity class's Context
    {

        //  MainActivity main=new MainActivity();
        //  Cursor cursor = main.setCursor();
        Integer contactsCount = cursor.getCount(); // get how many contacts you have in your contacts list
        phone=new String[contactsCount];
        name=new String[contactsCount];
        soundex=new String[contactsCount];
        type=new int[contactsCount];
        Contactscount=contactsCount;
        int count=0;

        if (contactsCount > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    //the below cursor will give you details for multiple contacts
                    Cursor pCursor = cntx.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);

                    name[count]=contactName;


                    //   SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                    //  SharedPreferences.Editor editor = preferences.edit();
                    //  editor.putString("Name","Harneet");
                    //  editor.apply();





                    // continue till this cursor reaches to all phone numbers which are associated with a contact in the contact list
                    while (pCursor.moveToNext()) {
                        int phoneType = pCursor.getInt(pCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));


                        //String isStarred 		= pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.STARRED));
                        String phoneNo = pCursor.getString(pCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        //you will get all phone numbers according to it's type as below switch case.
                        //Logs.e will print the phone number along with the name in DDMS. you can use these details where ever you want.


                        phone[count]=phoneNo;



                        switch (phoneType) {
                            case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                Log.e(contactName + ": TYPE_MOBILE", " " + phoneNo);
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                                Log.e(contactName + ": TYPE_HOME", " " + phoneNo);
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                                Log.e(contactName + ": TYPE_WORK", " " + phoneNo);
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_WORK_MOBILE:
                                Log.e(contactName + ": TYPE_WORK_MOBILE", " " + phoneNo);
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_OTHER:
                                Log.e(contactName + ": TYPE_OTHER", " " + phoneNo);
                                break;
                            default:
                                break;
                        }
                    }
                    pCursor.close();
                    count++;
                }
            }

            cursor.close();
        }

        //  com.example.halfm_1.tabbedview.soundex s;
        s=new   info.androidhive.materialtabs.activity.soundex();

        for(int i=0;i<120;i++){
            if(name[i].contains(" ")){

                String[] splited = name[i].split("\\s+");
                //  name[i]=  splited[0];
                soundex[i]=s.soundex(splited[0]);
            }
            else {
                soundex[i] = s.soundex(name[i]);
            }
            Log.e(soundex[i],"soundex of");


        }
    }



}
