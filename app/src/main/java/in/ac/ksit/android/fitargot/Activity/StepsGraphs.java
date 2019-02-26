package in.ac.ksit.android.fitargot.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import in.ac.ksit.android.fitargot.R;

public class StepsGraphs extends AppCompatActivity {
    float steps[]={2304f,2589f,10378f,937f,1432f,8765f,3719f};
    String dayname[]={"Monday","Tuesday","wednesday","Thursday","Friday","Saturday","Sunday"};

    float steps1[]={3304f,2589f,18378f,1937f,2432f,9765f,9719f};
    String dayname1[]={"Monday","Tuesday","wednesday","Thursday","Friday","Saturday","Sunday"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_graphs);
        setupPieChart();
        setupPieChart2();

    }
    private void setupPieChart(){
        List<PieEntry> pieEntry=new ArrayList<>();
        for(int i=0;i<dayname.length;i++)
        {
            pieEntry.add(new PieEntry(steps[i],dayname[i]));
        }
        PieDataSet dataset=new PieDataSet(pieEntry,"Calories");
        dataset.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData data=new PieData(dataset);

        PieChart chart=(PieChart)findViewById(R.id.chart1);
        chart.setData(data);
        chart.animateY(1000);
        chart.invalidate();
        setupPieChart2();
    }
    private void setupPieChart2()
    {
        List<PieEntry> pieEntry=new ArrayList<>();
        for(int i=0;i<dayname1.length;i++)
        {
            pieEntry.add(new PieEntry(steps1[i],dayname1[i]));
        }
        PieDataSet dataset=new PieDataSet(pieEntry,"Steps");
        dataset.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData data=new PieData(dataset);

        PieChart chart=(PieChart)findViewById(R.id.chart5);
        chart.setData(data);
        chart.animateY(1000);
        chart.invalidate();
    }
}
