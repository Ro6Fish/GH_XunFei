# GH_XunFei

科大讯飞语音功能

### 封装的工具使用：


```

在Application中注册科大讯飞
VoiceUtil.register(getApplicationContext(), getPackageName());

调用play进行对文本的语音播报，第一个参数Context，第二个参数String
VoiceUtil.play(MainActivity.this, etContent.getText().toString());

```



### 科大讯飞功能：



```

    btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //1.创建SpeechSynthesizer对象, 第二个参数：本地合成时传InitListener
                SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer(MainActivity.this, new InitListener() {
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
                String text = etContent.getText().toString();

                if (TextUtils.isEmpty(text)) {
                    text = "科大讯飞，让世界聆听我们的声音";
                }
                mTts.startSpeaking(text, mSynListener);
            }
    });

    //合成监听器
    private SynthesizerListener mSynListener = new SynthesizerListener() {

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

```
