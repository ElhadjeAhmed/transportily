package mechache.ahmed.instgrameapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class StudentAdapter  extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {




    int cptg = 0;//le conte generale 27/3/24
    //RNM

    mechache.ahmed.instgrameapp.RetriveDqtqlnRecyclerViez RetriveDqtqlnRecyclerViez;
    List<StudentModel> studentModelList;





    //RNM
    public StudentAdapter(RetriveDqtqlnRecyclerViez context, List<StudentModel> studentModelList) {
        //RNM
        this.RetriveDqtqlnRecyclerViez = context;
        this.studentModelList = studentModelList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.desgin_row_for_recyclerview,parent,false);

        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        extra p1 = new extra();
        int t = p1.siniale;
        if (t == 5) {
            System.out.println(t + " 5 *********************************************************");
        } else  {
            System.out.println(t + " 7 *********************************************************");
        }








        StudentModel studentModel = studentModelList.get(position);

        //

//



        holder.tvLast.setText(studentModel.getReservationDate()+" " + studentModel.getSource()+" vers "+studentModel.getDestination());
        holder.tvFirst.setText(studentModel.getCamion());
        String imageUri = null;
        imageUri =studentModel.getImage();
        Picasso.get().load(imageUri).into(holder.imageView);
        //recycler







        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extra p1 = new extra();
                p1.setSs(studentModel.getSource());// getName()
                p1.setDate(studentModel.getTelephone());//getDate()
                p1.setFn( studentModel.getDestination());//getLastName()
                p1.setLs(studentModel.getCamion());//getFirstName()
                p1.setUrl(studentModel.getImage());
                p1.setEd(studentModel.getIdp());//id poste
                p1.setMobil(studentModel.getReservationDate());//getPhoneAnouce()
                p1.setIdm(studentModel.getId());
                ///
                p1.setId(studentModel.getId());

                Intent intent = new Intent(RetriveDqtqlnRecyclerViez, detaille.class);
                intent.putExtra("object",(Serializable) p1);
                // int t =getsp();
                System.out.println( studentModel.getLastName()+"////////////////////////////////////////////////////////////////////////");
                System.out.println( studentModel.getFirstName()+"////////////////////////////////////////////////////////////////////////");
                System.out.println(studentModel.getDate()+"////////////////////////////////////////////////////////////////////////");
                System.out.println(studentModel.getImage()+"////////////////////////////////////////////////////////////////////////");
                System.out.println(studentModel.getId()+"////////////////////////////////////////////////////////////////////////");

                RetriveDqtqlnRecyclerViez.startActivity(intent);

            }


        });



    }


    @Override
    public int getItemCount() {
        return studentModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvFirst,tvLast;

        CardView recCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.image_recyclerView_id);
            tvFirst=itemView.findViewById(R.id.tvfirstname_recyelerview_id);
            tvLast=itemView.findViewById(R.id.tvlastName_recyclrView_id);
            recCard = itemView.findViewById(R.id.recCard);
        }
    }

}
