package sstorage.mobile.senai.com.sstorage.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sstorage.mobile.senai.com.sstorage.R;
import sstorage.mobile.senai.com.sstorage.model.Environment;
import sstorage.mobile.senai.com.sstorage.model.Movement;
import sstorage.mobile.senai.com.sstorage.view.holder.MovementPatrimonyItemViewHolder;

public class MovementPatrimonyAdapter extends RecyclerView.Adapter<MovementPatrimonyItemViewHolder> {

    private final Context context;
    private final List<Movement> movements;
    private final List<Environment> environments;

    public MovementPatrimonyAdapter(Context context, List<Movement> movements, List<Environment> environments) {
        this.context = context;
        this.movements = movements;
        this.environments = environments;
    }

    @NonNull
    @Override
    public MovementPatrimonyItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rvitem_movement_patrimony, parent, false);
        MovementPatrimonyItemViewHolder movementPatrimonyItemViewHolder = new MovementPatrimonyItemViewHolder(view);
        return movementPatrimonyItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovementPatrimonyItemViewHolder holder, int position) {
        Movement movement = movements.get(position);
        Environment origin = null, destiny = null;
        for (Environment environment : environments) {
            if(origin != null && destiny != null)
                break;
            if(environment.getId() == movement.getOriginEnvironmentId()) {
                origin = environment;
                continue;
            }
            if(environment.getId() == movement.getDestinyEnvironmentId())
                destiny = environment; // No continue needed, because it is the last operation at for
        }
        //
        holder.fill(movement, origin, destiny);
    }

    @Override
    public int getItemCount() {
        return movements.size();
    }
}
