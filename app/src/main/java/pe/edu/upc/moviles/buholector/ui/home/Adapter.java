package pe.edu.upc.moviles.buholector.ui.home;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import pe.edu.upc.moviles.buholector.R;
import pe.edu.upc.moviles.buholector.model.BookModel;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private final ArrayList<BookModel> books;
    private final boolean searching;

    public Adapter(ArrayList<BookModel> items,boolean searching) {
        books = items;
        this.searching=searching;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final BookModel b=books.get(position);
        holder.book = b;
        holder.more_button.setText(searching?"Añadir":"Ver");
        holder.author.setText(b.getAutor());
        holder.title.setText(b.getTitulo());
        if( !b.getImagen().isEmpty() && b.getImagen()!=null){
            try{
                byte[] decodedString = Base64.decode(b.getImagen(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                holder.image.setImageBitmap(decodedByte);
            }catch (Exception e){
                e.printStackTrace();
            }

           /* Picasso picasso=Picasso.with(holder.mView.getContext());
            picasso.setIndicatorsEnabled(true);
            picasso.load(b.getImagen())
                    .resize(200,200)
                    .centerCrop()
                    .into(holder.image);*/
        }
        holder.more_button.setOnClickListener(view -> {
            Toast.makeText(holder.mView.getContext(),b.getTitulo(),Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView title,author;
        public BookModel book;
        public  final ImageView image;
        public  final Button more_button;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            title = view.findViewById(R.id.title);
            author = view.findViewById(R.id.author);
            image=view.findViewById(R.id.image);
            more_button=view.findViewById(R.id.more_button);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }
    }
}
