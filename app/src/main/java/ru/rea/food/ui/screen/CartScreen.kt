package ru.rea.food.ui.screen

import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.rea.food.Checkout
import ru.rea.food.R
import ru.rea.food.ui.Button
import ru.rea.food.ui.item.CartItem
import ru.rea.food.ui.topbar.TopAppBar
import ru.rea.food.vm.MenuViewModel
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "CartScreen"

@Composable
fun CartScreen(
    viewModel: MenuViewModel,
    onExit: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        TopAppBar(onExit) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
        Column(
            Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.cart),
                Modifier.padding(start = 16.dp),
                style = MaterialTheme.typography.h5
            )
            LazyColumn(
                Modifier
                    .weight(1f)
                    .padding(vertical = 16.dp)
            ) {
                viewModel.cart?.let {
                    items(it.items) {
                        CartItem(it, viewModel)
                    }
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(R.string.total))
                Text(
                    text = "₽" + (viewModel.cart?.finalAmount ?: 0),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            val context = LocalContext.current
            Button(text = stringResource(R.string.pay)) {
                val cal = Calendar.getInstance()
                TimePickerDialog(
                    context,
                    R.style.picker,
                    { _, _, _ ->
                        val checkout = context.getString(R.string.checkout)
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(checkout)))
                    },
                    cal.get(Calendar.HOUR_OF_DAY),
                    cal.get(Calendar.MINUTE),
                    true
                ).show()
            }
        }
    }
}