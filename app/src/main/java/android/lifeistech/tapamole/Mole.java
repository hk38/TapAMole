package android.lifeistech.tapamole;

import android.widget.ImageView;

// モグラのクラス
public class Mole {
    // 現在の状態を格納する変数
    int state;
    // モグラの画像を表示するImageView
    ImageView moleImg;
    // ハンドラ
    android.os.Handler h;
    Runnable hide;

    // コンストラクタ
    public Mole(ImageView iv){
        // 状態を0に設定
        state = 0;
        // 渡されたImageViewを格納
        moleImg = iv;
        // ImageViewに隠れた状態のモグラの画像をセット
        moleImg.setImageResource(R.drawable.mole1);

        // ハンドラと並列処理
        h = new android.os.Handler();
        hide = new Runnable() {
            @Override
            public void run() {
                state = 0;

                moleImg.setImageResource(R.drawable.mole1);
            }
        };
    }

    public void start(){
        if(state == 0){
            state = 1;
            moleImg.setImageResource(R.drawable.mole2);
            h.postDelayed(hide, 1000);
        }
    }

    public int tapMole(){
        if(state == 1){
            state = 2;
            moleImg.setImageResource(R.drawable.mole3);

            h.removeCallbacks(hide);
            h.postDelayed(hide, 1000);
            return 1;
        }
        return 0;
    }
}
