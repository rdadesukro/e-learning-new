//package com.example.e_learning_penjas;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import com.google.android.youtube.player.YouTubeBaseActivity;
//import com.google.android.youtube.player.YouTubeInitializationResult;
//import com.google.android.youtube.player.YouTubePlayer;
//import com.google.android.youtube.player.YouTubePlayerView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class menu_detail_vidio extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
//
//    @BindView(R.id.player)
//    YouTubePlayerView player;
//    private static final int RECOVERY_REQUEST = 1;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.menu_detail_vidio);
//        ButterKnife.bind(this);
//    }
//    @Override
//    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
//        if (!wasRestored) {
//            player.cueVideo("fhWaJi1Hsfo"); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
//        }
//    }
//
//    @Override
//    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//        if (youTubeInitializationResult.isUserRecoverableError()) {
//            youTubeInitializationResult.getErrorDialog(this, RECOVERY_REQUEST).show();
//        } else {
//         //   String error = String.format(getString(R.string.player_error), errorReason.toString());
//           // Toast.makeText(this, error, Toast.LENGTH_LONG).show();
//        }
//    }
//
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == RECOVERY_REQUEST) {
//            // Retry initialization if user performed a recovery action
//            getYouTubePlayerProvider().initialize("AIzaSyDKXa1Mr2Gzc6YNXwWxSgJYpYj7dvZsEC8", this);
//        }
//    }
//
//    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
//        return player;
//    }
//}