package com.example.fakecall ;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
//import android.support.v4.view.accessibility.AccessibilityEventCompat;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.InterstitialAd;

import static android.view.WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
import static android.view.WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fakecall.Fagment.TabViewDataSingleton;

public class CallActivity extends AppCompatActivity {
    ImageView accept;
    RelativeLayout attend;
    boolean attended = false;
    LinearLayout background;
    ImageView callerImg;
    RelativeLayout calling;
    Boolean check = Boolean.FALSE;
    Context context;
    ImageView imageView;
    TextView inCall;
    TextView incomming;
    boolean locked;
    AudioManager mAudioManager;
    String mPath;
    Thread main;
    int min = 0;
    MediaPlayer mp;
    TextView nameText;
    int originalVolume;
    TextView phoneText , title;
    Ringtone r;
    ImageView reject;
    int sec = 0;
    SharedPreferences sharedPref;
    Thread t;
    SelectSreen selectSreen;
    //
    Boolean check1 = Boolean.FALSE;
    Boolean checkCalender = Boolean.TRUE;
    Boolean checkNote = Boolean.TRUE;
    Boolean checkAddcall = Boolean.TRUE;
    Boolean checkPerson = Boolean.TRUE;
    Boolean checkMute = Boolean.TRUE;
    //
    ImageView hold , calenda , note , addcall , person , mute ;
//    InterstitialAd mInterstitialAd;
//    AdRequest adRequestint;

    @TargetApi(16)
    protected void onCreate(Bundle savedInstanceState) {
        //locked = ((KeyguardManager) getSystemService("keyguard")).inKeyguardRestrictedInputMode();
        context = this;
        Window window = getWindow();
        //window.addFlags(AccessibilityEventCompat.TYPE_WINDOWS_CHANGED);
        window.addFlags(FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(FLAG_TURN_SCREEN_ON);
        super.onCreate(savedInstanceState);
        switch (selectSreen.idlayout)
        {
            case 0 :
                setContentView(R.layout.activity_call_oppo);
                break;
            case 1 :
                setContentView(R.layout.activity_call2);
                break;
            case 2 :
                setContentView(R.layout.samsunga10);
                break;
            case 3:
                setContentView(R.layout.call4);
                break;

        }

       // loadInterstitialAd();
        calling = (RelativeLayout) findViewById(R.id.calling);
        callerImg = (ImageView) findViewById(R.id.caller_image);
        attend = (RelativeLayout) findViewById(R.id.attend);
        incomming = (TextView) findViewById(R.id.timer);
        inCall = (TextView) findViewById(R.id.text_in_call);
        imageView = (ImageView) findViewById(R.id.speaker);
        hold = (ImageView) findViewById(R.id.imgHold) ;
        calenda = (ImageView) findViewById(R.id.imgCalender) ;
        note = (ImageView) findViewById(R.id.imgNote) ;
        addcall = (ImageView) findViewById(R.id.imgaddCall) ;
        person = (ImageView) findViewById(R.id.imgcontacts) ;
        mute = (ImageView) findViewById(R.id.imgmute) ;
        //
        context = this;
        calling = (RelativeLayout) findViewById(R.id.calling);
        title = (TextView) findViewById(R.id.title);
        callerImg = (ImageView) findViewById(R.id.caller_image);
        attend = (RelativeLayout) findViewById(R.id.attend);
        incomming = (TextView) findViewById(R.id.timer);
       // inCall = (TextView) findViewById(R.id.text_in_call);
        imageView = (ImageView) findViewById(R.id.speaker);
        sharedPref = getSharedPreferences("file", 0);
        nameText = (TextView) findViewById(R.id.caller_name);
        phoneText = (TextView) findViewById(R.id.caller_number);
        accept = (ImageView) findViewById(R.id.gif_answer);
        reject = (ImageView) findViewById(R.id.gif_reject);
        main = Thread.currentThread();
        main.setName("Main Thread");
        setCaller();

    }
//    private void loadInterstitialAd() {
//        adRequestint = new AdRequest.Builder().addTestDevice("0224C93FFD644350DCD7F3D7557C6A5C").build();
//        mInterstitialAd = new InterstitialAd(getApplicationContext());
//        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_full_screen));
//        mInterstitialAd.loadAd(adRequestint);
//    }
//    private void showInterstitial() {
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        }
//    }

    void ring() {
        try {
            String path = sharedPref.getString("ring", "");
            if (path.equals("")) {
                r = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(1));
                r.play();
                return;
            }
            ringFromMedia(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void answer() {
        if (sharedPref.getString("ring", "").equals("")) {
            r.stop();
        } else {
            mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            mAudioManager.setStreamVolume(3, originalVolume, 0);
            if (mp != null) {
                mp.stop();
                mp.release();
            }
        }
        title.setVisibility(View.GONE);
        calling.setVisibility(View.GONE);
        attend.setVisibility(View.VISIBLE);
        inCall.setVisibility(View.VISIBLE);
        incomming.setVisibility(View.VISIBLE);
        audioPlayer(sharedPref.getString("audio", ""));
        attended = true;
        t = new Thread() {
            public void run() {
                try {
                    Log.v("NewMyThread", t.getName());
                    while (true) {
                        Thread.sleep(1000);
                        if (main.isAlive()) {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    incomming.setText(updateTime());
                                }
                            });
                        } else {
                            return;
                        }
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }

    private String updateTime() {
        sec++;
        String time = "";
        if (sec < 60) {
            if (min < 10) {
                time = time + "0" + min;
            } else {
                time = time + min;
            }
            time = time + ":";
            if (sec < 10) {
                return time + "0" + sec;
            }
            return time + sec;
        }
        min++;
        sec = 0;
        if (min < 10) {
            time = time + "0" + min;
        } else {
            time = time + min;
        }
        time = time + ":";
        if (sec < 10) {
            return time + "0" + sec;
        }
        return time + sec;
    }

    public void decline() {
        if (sharedPref.getString("ring", "").equals("") && r != null) {
            r.stop();
        }
        startActivity(new Intent(this, FragmentHome.class));
        finish();
    }

    public void OnClickSpeaker(View view) {
        if (check) {
            mp.pause();
            int length = mp.getCurrentPosition();
            mp.stop();
            mp.release();
            audioResume(length);

            switch (selectSreen.idlayout)
            {
                case 0:
                    imageView.setImageResource(R.drawable.speaker);
                    break;
                case 3 :
                    imageView.setImageResource(R.drawable.ic_baseline_volume_up_240123);
                    break;
            }
            check = Boolean.FALSE;
            return;
        }
        mp.pause();
        int length = mp.getCurrentPosition();
        mp.stop();
        mp.release();
        audioResume(length);
        switch (selectSreen.idlayout)
        {
            case 0:
                imageView.setImageResource(R.drawable.speaker2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.ic_baseline_volume_up_24);
                break;
        }

        check = Boolean.TRUE;
    }

    void audioResume(int len) {
        mp = new MediaPlayer();
        try {
            if (check) {
                mp.setAudioStreamType(0);
            } else {
                mp.setAudioStreamType(3);
            }
            mp.setDataSource(mPath);
            mp.prepare();
            mp.seekTo(len);
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void audioPlayer(String path) {
        mPath = path;
        mp = new MediaPlayer();
        if(mp!=null){
            try {
                mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                originalVolume = mAudioManager.getStreamVolume(3);
                mAudioManager.setStreamVolume(3, mAudioManager.getStreamMaxVolume(3), 0);
                mp.setAudioStreamType(0);
                mp.setDataSource(path);
                mp.prepare();
                mp.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void ringFromMedia(String path) {
        mPath = path;
        mp = new MediaPlayer();
        try {
            mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            originalVolume = mAudioManager.getStreamVolume(3);
            mAudioManager.setStreamVolume(3, mAudioManager.getStreamMaxVolume(3), 0);
            mp.setAudioStreamType(3);
            mp.setDataSource(path);
            mp.prepare();
            mp.start();
            mp.setOnCompletionListener(new OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    mAudioManager.setStreamVolume(3, originalVolume, 0);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @TargetApi(16)
    void setCaller() {
        String name = sharedPref.getString("name", "");
        String phone = sharedPref.getString("number", "");
        String image = sharedPref.getString("image", "");
        Log.e("TAG", "setCaller: "+image );
        nameText.setText(name);
        phoneText.setText(phone);
        int obj = -1;
//        switch (image.hashCode()) {
//            case 0:
//                if (image.equals("")) {
//                    obj = 0;
//                    break;
//                }
//                break;
//            case 48:
//                if (image.equals("0")) {
//                    obj = 1;
//                    break;
//                }
//                break;
//            case 49:
//                if (image.equals("1")) {
//                    obj = 2;
//                    break;
//                }
//                break;
//            case 50:
//                if (image.equals("2")) {
//                    obj = 3;
//                    break;
//                }
//                break;
//            case 51:
//                if (image.equals("3")) {
//                    obj = 4;
//                    break;
//                }
//                break;
//            case 52:
//                if (image.equals("4")) {
//                    obj = 5;
//                    break;
//                }
//                break;
//        }
//        switch (obj) {
//            case 0:
//                callerImg.setImageResource(R.drawable.person);
//                return;
//            case 1:
//                callerImg.setImageResource(R.drawable.gallery_btn_0);
//                return;
//            case 2:
//                callerImg.setImageResource(R.drawable.gallery_btn_1);
//                return;
//            case 3:
//                callerImg.setImageResource(R.drawable.gallery_btn_2);
//                return;
//            case 4:
//                callerImg.setImageResource(R.drawable.gallery_btn_3);
//                return;
//            case 5:
//                callerImg.setImageResource(R.drawable.gallery_btn_4);
//                return;
//            default:
             //   callerImg.setImageBitmap(TabViewDataSingleton.getImg());
        Glide.with(this)
                .load(TabViewDataSingleton.getImg())
                .asBitmap()
                .into(callerImg) ;
        //}
    }

    public void onBackPressed() {
    }

    protected void onStop() {
        super.onStop();
        String path = sharedPref.getString("ring", "");
        if (r != null && path.equals("")) {
            r.stop();
        }
        if (mp != null) {
            try {
                if (mp.isPlaying()) {
                    mp.stop();
                    mp.release();
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.setStreamVolume(3, originalVolume, 0);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStart() {
        super.onStart();
        ring();
    }

    public void reciveCall(View view) {
        answer();
    }
    public void rejectCall(View view) {
        decline();
    }

    public void endCall(View view) {
        startActivity(new Intent(this, FragmentHome.class));
        //showInterstitial();
        finish();
    }


    public void OnClickcalende(View view) {
        // boolean checkCalender = true ;
        if (checkCalender) {
            switch (selectSreen.idlayout)
            {
                case 0 :
                    calenda.setImageResource(R.drawable.ic_baseline_perm_contact_calendar_24);
                    break;
                case 1 :
                    calenda.setImageResource(R.drawable.ic_baseline_perm_contact_calendar_24);
                    break;
                case 2 :
                    calenda.setImageResource(R.drawable.ic_baseline_voicemail_24444);
                    break ;
                case 3 :
                    calenda.setImageResource(R.drawable.addcall);
                    break ;
            }
            checkCalender = Boolean.FALSE;
            return;
        }
        switch (selectSreen.idlayout)
        {
            case 0 :
                calenda.setImageResource(R.drawable.ic_perm_contact_calendar_black_24dp);
                break ;
            case 1 :
                calenda.setImageResource(R.drawable.ic_perm_contact_calendar_black_24dp);
                break ;
            case 2 :
                calenda.setImageResource(R.drawable.ic_baseline_voicemail_24);
                break ;
            case 3 :
                calenda.setImageResource(R.drawable.addcallll);
                break ;
        }

        checkCalender = Boolean.TRUE;
    }

    public void OnClickNote(View view) {
        if (checkNote) {
            switch (selectSreen.idlayout)
            {
                case 0:
                    note.setImageResource(R.drawable.note);
                    break ;
                case 1 :
                    note.setImageResource(R.drawable.note);
                    break ;
                case 2:
                    note.setImageResource(R.drawable.ic_baseline_bluetooth_24444);
                    break ;
                case 3:
                    note.setImageResource(R.drawable.ic_baseline_bluetooth_24);
                    break ;
            }

            checkNote = Boolean.FALSE;
            return;
        }
        switch (selectSreen.idlayout)
        {
            case 0:
                note.setImageResource(R.drawable.ic_note_add_black_24dp);
                break ;
            case 1 :
                note.setImageResource(R.drawable.ic_note_add_black_24dp);
                break ;
            case 2:
                note.setImageResource(R.drawable.ic_baseline_bluetooth_24);
                break ;
            case 3:
                note.setImageResource(R.drawable.ic_baseline_bluetooth_24444);
                break ;
        }

        checkNote = Boolean.TRUE;
    }

    public void OnClickAddcal(View view) {
        if (checkAddcall) {
            switch (selectSreen.idlayout)
            {
                case 0 :
                    addcall.setImageResource(R.drawable.addcall);
                    break ;
                case 1 :
                    addcall.setImageResource(R.drawable.addcall);
                    break ;
                case 2 :
                    addcall.setImageResource(R.drawable.ic_baseline_volume_up_24);
                    break ;
                case 3 :
                    addcall.setImageResource(R.drawable.ic_baseline_volume_up_240123);
                    break ;
            }
            checkAddcall = Boolean.FALSE;
            return;

        }
        switch (selectSreen.idlayout) {
            case 0:
                addcall.setImageResource(R.drawable.addcallll);
                break ;
            case 1:
                addcall.setImageResource(R.drawable.addcallll);
                break ;
            case 2:
                addcall.setImageResource(R.drawable.ic_baseline_volume_up_240123);
                break ;
            case 3:
                addcall.setImageResource(R.drawable.ic_baseline_volume_up_24);
                return;
        }
        checkAddcall = Boolean.TRUE;
    }

    public void OnClickContacs(View view) {
        if (checkPerson) {
            switch (selectSreen.idlayout)
            {
                case 0:
                    person.setImageResource(R.drawable.ic_baseline_person_pin_24);
                    break;
                case 1 :
                    person.setImageResource(R.drawable.ic_baseline_person_pin_24);
                    break;
                case 2:
                    person.setImageResource(R.drawable.ic_baseline_dialpad_2444);
                    break;
                case 3 :
                    person.setImageResource(R.drawable.ic_mic_black_24dp);
                    break;
            }

            checkPerson = Boolean.FALSE;
            return;
        }
        switch (selectSreen.idlayout)
        {
            case 0:
                person.setImageResource(R.drawable.ic_baseline_person_pin_24444);
                break;
            case 1 :
                person.setImageResource(R.drawable.ic_baseline_person_pin_24444);
                break;
            case 2:
                person.setImageResource(R.drawable.ic_baseline_dialpad_24);
                break;
            case 3 :
                person.setImageResource(R.drawable.ic_baseline_mic_none_244444);
                break;
        }

        checkPerson = Boolean.TRUE;
    }

    public void OnClickMic(View view) {
        if (checkMute) {
            switch (selectSreen.idlayout)
            {
                case 0 :
                    mute.setImageResource(R.drawable.ic_baseline_mic_24);
                    break;
                case 1 :
                    mute.setImageResource(R.drawable.ic_baseline_mic_24);
                    break;
                case 2:
                    mute.setImageResource(R.drawable.ic_baseline_mic_24);
                    break;
                case 3 :
                    mute.setImageResource(R.drawable.ic_baseline_dialpad_24);
                    break;
            }
            checkMute = Boolean.FALSE;
            return;
        }
        switch (selectSreen.idlayout)
        {
            case 0 :
                mute.setImageResource(R.drawable.ic_mic_black_24dp);
                break;
            case 1 :
                mute.setImageResource(R.drawable.ic_mic_black_24dp);
                break;
            case 2 :
                mute.setImageResource(R.drawable.ic_mic_black_24dp);
                break;
            case 3 :
                mute.setImageResource(R.drawable.ic_baseline_dialpad_2444);
                break;
        }
        checkMute = Boolean.TRUE;
    }
    public  void OnClickHold(View view) {
        if (check) {
            switch (selectSreen.idlayout)
            {
                case 0 :
                    hold.setImageResource(R.drawable.ic_baseline_call_24);
                    break;
                case 1:
                    hold.setImageResource(R.drawable.ic_baseline_call_24);
                    break;
                case  2 :
                    hold.setImageResource(R.drawable.ic_baseline_call_24);
                    break;
                case 3 :
                    hold.setImageResource(R.drawable.call);
                    break;
            }
            check = Boolean.FALSE;
            return;
        }
        switch (selectSreen.idlayout)
        {
            case 0 :
                hold.setImageResource(R.drawable.call);
                break;
            case 1:
                hold.setImageResource(R.drawable.call);
                break;
            case  2 :
                hold.setImageResource(R.drawable.call);
                break;
            case  3 :
                hold.setImageResource(R.drawable.ic_baseline_call_24);
                break;
        }

        check = Boolean.TRUE;
    }
}
