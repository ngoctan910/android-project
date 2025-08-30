package ngoctan.app.traininng.androidproject.ui.common
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class DataViewHolder<out T: ViewBinding> (val binding: T): RecyclerView.ViewHolder(binding.root) {
    var currentVelocity = 0f
    val rotation: SpringAnimation = SpringAnimation(itemView, SpringAnimation.ROTATION)
        .setSpring(
            SpringForce()
                .setFinalPosition(0f)
                .setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY)
                .setStiffness(SpringForce.STIFFNESS_VERY_LOW)
        )
        .addUpdateListener { _, _, velocity ->
            currentVelocity = velocity
        }
}