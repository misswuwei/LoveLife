package Holder;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import Date.DateFather;
import Date.WeatherJsonDate;
import lovelife.xiangmu.wuwei.lovelife.R;

/**
 * Created by Wuwei on 2018/3/5.
 */

public class WeatherTypeHolder extends absHolder {
    TextView riqi;
    TextView duoyun;
    TextView wendu;
    public WeatherTypeHolder(View itemView) {
        super(itemView);
        riqi = itemView.findViewById(R.id.tv_riqi);
        duoyun = itemView.findViewById(R.id.tv_duoyun);
        wendu = itemView.findViewById(R.id.tv_wendu);
    }

    @Override
    public void buileHoulder(DateFather date, int position) {

        riqi.setText(((WeatherJsonDate)date).date);
        duoyun.setText(((WeatherJsonDate)date).weather);
        wendu.setText(((WeatherJsonDate)date).low_temper+"/"+((WeatherJsonDate)date).high_temper);
    }
}
