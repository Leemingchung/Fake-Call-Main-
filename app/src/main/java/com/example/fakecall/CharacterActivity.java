package com.example.fakecall;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.techx.fakecallprank.Charactor;

import java.util.ArrayList;

public class CharacterActivity extends Fragment {
    static final /* synthetic */ boolean $assertionsDisabled = (!CharacterActivity.class.desiredAssertionStatus());
    public static int[] prgmImages = new int[]{R.drawable.gallery_btn_0, R.drawable.gallery_btn_1, R.drawable.gallery_btn_2, R.drawable.gallery_btn_3, R.drawable.gallery_btn_4};
    public static String[] prgmNameList = new String[]{"Police", "Pizza", "Girl Friend", "MOM", "Santa Claus"};
    public static String[] prgmPhoneList = new String[]{"15", "03126688776", "03007865456", "0426754346", "0548755726"};
    CharAdapter adapter;
    ArrayList<Charactor> categroyList;
    Intent returnIntent;
    //dView mAdView;

    public boolean onBackPressed() {
      //  super.onBackPressed();
        getActivity().setResult(0, returnIntent);
      //  getActivity().finish();
        return false;
    }

//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_character);
@Nullable
@Override
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
{
        View view = inflater.inflate(R.layout.activity_character ,container, false) ;

        //ADS
        //mAdView = (AdView) findViewById(R.id.banner_AdView);
       // AdRequest adRequest = new AdRequest.Builder().addTestDevice("0224C93FFD644350DCD7F3D7557C6A5C").build();
        //mAdView.loadAd(adRequest);
        categroyList = new ArrayList();
        ListView listView = view.findViewById(R.id.list_view);
        for (int i = 0; i < prgmNameList.length; i++) {
            categroyList.add(new Charactor(prgmNameList[i], prgmPhoneList[i], prgmImages[i]));
        }
        adapter = new CharAdapter(getActivity(), categroyList);
        if ($assertionsDisabled || listView != null) {
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    characterClick(position);
                }
            });
            returnIntent = new Intent();
            return view;
        }
        throw new AssertionError();
    }

    public void characterClick(int pos) {

        switch (pos) {
            case 0:
                returnIntent.putExtra("name", "Police");
                returnIntent.putExtra("number", "031255844");
                returnIntent.putExtra("img", "0");
                Bundle bundle = new Bundle() ;
                bundle.putString("name" , " chung ");
                bundle.putString("number" , " 031255844");
                bundle.putString("img" , "0");
                getParentFragmentManager().setFragmentResult("data1" , bundle);
                //getActivity().setResult(-1, returnIntent);
//                getActivity().finish();
                return;
            case 1:
                returnIntent.putExtra("name", "Pizza");
                returnIntent.putExtra("number", "03126688776");
                returnIntent.putExtra("img", "1");
                Bundle bundle1 = new Bundle() ;
                bundle1.putString("name", "Pizza");
                bundle1.putString("number", "03126688776");
                bundle1.putString("img" , "");
                getParentFragmentManager().setFragmentResult("data1" , bundle1);
              //  getActivity().setResult(-1, returnIntent);
//                getActivity().finish();
                return;
            case 2:
                returnIntent.putExtra("name", "Girl Friend");
                returnIntent.putExtra("number", "03007865456");
                returnIntent.putExtra("img", "2");
                //
                Bundle bundle2 = new Bundle() ;
                bundle2.putString("name", "Girl Friend");
                bundle2.putString("number", "03126688776");
                bundle2.putString("img" , " 2");
                getParentFragmentManager().setFragmentResult("data1" , bundle2);
                getActivity().setResult(-1, returnIntent);
    //            getActivity().finish();
                return;
            case 3:
                returnIntent.putExtra("name", "MOM");
                returnIntent.putExtra("number", "0426754346");
                returnIntent.putExtra("img", "3");
                Bundle bundle3 = new Bundle() ;
                bundle3.putString("name", "MOM");
                bundle3.putString("number", "0426754346");
                bundle3.putString("img" , " 3");
                getParentFragmentManager().setFragmentResult("data1" , bundle3);
//                getActivity().setResult(-1, returnIntent);
//                getActivity().finish();
                return;
            case 4:
                returnIntent.putExtra("name", "Santa Claus");
                returnIntent.putExtra("number", "0548755726");
                returnIntent.putExtra("img", "4");
                Bundle bundle4 = new Bundle() ;
                bundle4.putString("name", "Santa Claus");
                bundle4.putString("number", "0548755726");
                bundle4.putString("img" , " 4");
                getParentFragmentManager().setFragmentResult("data1" , bundle4);
//                getActivity().setResult(-1, returnIntent);
//                getActivity().finish();
                return;
            default:
        }
    }
}