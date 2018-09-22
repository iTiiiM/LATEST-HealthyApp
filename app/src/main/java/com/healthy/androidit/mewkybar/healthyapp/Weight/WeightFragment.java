package com.healthy.androidit.mewkybar.healthyapp.Weight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.healthy.androidit.mewkybar.healthyapp.R;

import java.util.ArrayList;
import java.util.Comparator;

public class WeightFragment extends Fragment {
    private String uid;
    private FirebaseAuth _user = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    ArrayList<Weight> weights = new ArrayList<Weight>();
    private DocumentSnapshot doc;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.uid = _user.getUid();
        final ListView _weightList = getView().findViewById(R.id.weight_list);
        final WeightAdapter _weightAdapter = new WeightAdapter(
                getActivity(),
                R.layout.fragment_weight_item,
                weights
        );
        Button addWeightButton = (Button) getView().findViewById(R.id.add_weight_button);
    addWeightButton.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view,
                    new WeightForm()).commit();

        }
    });
        _weightList.setAdapter(_weightAdapter);


        db.collection("myfitness").document(uid).collection("weight").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot document : task.getResult()){
                                weights.add(document.toObject(Weight.class));
                            }
                        }
                        updateStatusToFirestore(weights);
                        _weightAdapter.notifyDataSetChanged();

                        }
                    });

//        _weight.add("64");
//        _weight.add("80");
//        _weight.add("75");
//
//        ArrayAdapter<String> _weightAdapter = new ArrayAdapter<>(
//                getActivity(),
//                android.R.layout.simple_list_item_1,
//                _weight
//        );
//
//        ListView _weightList = (ListView) getView().findViewById(R.id.weight_list);
//        _weightList.setAdapter(_weightAdapter);

    }

    private void updateStatusToFirestore(ArrayList<Weight> weights){
        uid = _user.getUid().toString();
        this.weights = weights;
        int index = 0;
        for(Weight items: this.weights){
            db.collection("myfitness").document(uid).collection("weight")
                    .document(items.getDate()).set(this.weights.get(index++));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight
                , container, false);
    }
}
