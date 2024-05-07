package id.ac.ub.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    Context _context;
    List<Item> data;
    public Adapter(Context _context, List<Item> data) {
        this._context = _context;
        this.data = data;
        this.inflater = LayoutInflater.from(this._context);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rowText;
        TextView rowID;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowText = (TextView) itemView.findViewById(R.id.rowText);
            rowID = (TextView) itemView.findViewById(R.id.rowID);
        }

    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Item item = data.get(position);
        holder.rowText.setText(item.getJudul());
        holder.rowID.setText(Integer.toString(item.getId()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
