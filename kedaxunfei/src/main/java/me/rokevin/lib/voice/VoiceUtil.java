package me.rokevin.lib.voice;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;

/**
 * Created by luokaiwen on 16/10/18.
 */
public class VoiceUtil {

    public VoiceUtil() {

    }

    /**
     * 注册科大讯飞
     *
     * @param context
     * @param packageName
     */
    public static void register(Context context, String packageName) {

        String appId = getAppId(context);

//        if (BuildConfig.DEBUG) {
//            Log.e(VoiceUtil.class.getSimpleName(), "appId:" + appId);
//        }

        SpeechUtility.createUtility(context, SpeechConstant.APPID + "=" + appId);
    }

    /**
     * 获取科大讯飞的appId
     *
     * @param context
     * @return
     */
    private static String getAppId(Context context) {

        String appId = "";

        ApplicationInfo info;

        try {
            info = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            appId = info.metaData.getString("IFLYTEK_APPKEY");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return appId;
    }

    /**
     * 语音播报
     *
     * @param context
     * @param content
     */
    public static void play(Context context, String content) {

        //1.创建SpeechSynthesizer对象, 第二个参数：本地合成时传InitListener
        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer(context, new InitListener() {
            @Override
            public void onInit(int i) {

            }
        });
        //2.合成参数设置，详见《科大讯飞MSC API手册(Android)》SpeechSynthesizer 类
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");//设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "100");//设置音量，范围0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        //设置合成音频保存位置（可自定义保存位置），保存在“./sdcard/iflytek.pcm”
        //保存在SD卡需要在AndroidManifest.xml添加写SD卡权限
        //如果不需要保存合成音频，注释该行代码
        //mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");
        //3.开始合成
        mTts.startSpeaking(content, mSynListener);
    }

    /**
     * 合成监听器
     */
    private static SynthesizerListener mSynListener = new SynthesizerListener() {

        //会话结束回调接口，没有错误时，error为null
        public void onCompleted(SpeechError error) {
        }

        //缓冲进度回调
        //percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在文本中结束位置，info为附加信息。
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
        }

        //开始播放
        public void onSpeakBegin() {

            Log.e("TAG", "开始播放");
        }

        //暂停播放
        public void onSpeakPaused() {

            Log.e("TAG", "开始暂停");
        }

        //播放进度回调
        //percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文本中结束位置.
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
        }

        //恢复播放回调接口
        public void onSpeakResumed() {
        }

        //会话事件回调接口
        public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {
        }
    };
}
