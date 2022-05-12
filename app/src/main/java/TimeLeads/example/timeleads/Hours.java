package TimeLeads.example.timeleads;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.timeleads.R;

public class Hours extends Fragment {

    private ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.hours,container, false);
        ((MainActivity) getActivity()).setActionBarTitle("Horas complementares");
        TextView txtProgress = (TextView) rootview.findViewById(R.id.txtProgress);
        progressBar = (ProgressBar) rootview.findViewById(R.id.progressBar);
        progressBar.setProgress(50);
        txtProgress.setText("Ola Gabriel, você complementou " + progressBar.getProgress() + "% das suas horas complementares!");
        return rootview;
    }
}
