package ru.rea.food.ui.screen

import android.graphics.Bitmap
import android.graphics.Color
import android.icu.util.GregorianCalendar
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.coroutines.launch
import ru.rea.food.R
import ru.rea.food.REAFoodService
import ru.rea.food.ui.Button
import ru.rea.food.ui.TextField
import ru.rea.food.ui.topbar.TopAppBar
import java.util.*

private const val TAG = "CheckoutScreen"

private const val CARD = 16
private const val EXP = 4
private const val VER = 3

@Composable
fun CheckoutScreen(token: String, nav: NavController) {
    var dialog by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(onClick = { nav.popBackStack() }, onHelp = {
            dialog =
                "Комментарий можно оставить пустым. При указании текущего или прошедшего времени заказ начнет готовиться сейчас."
        }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }
        Text(
            text = "Оплата",
            modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 16.dp),
            style = MaterialTheme.typography.h5,
        )
        var comment by remember { mutableStateOf("") }
        TextField(
            text = comment,
            onTextChange = { comment = it },
            label = "Введите комментарий",
            title = "Комментарий к заказу",
            capitalize = true
        )
        var card by remember { mutableStateOf("") }
        TextField(
            text = card,
            onTextChange = { if (it.isDigitsOnly() && it.length <= CARD) card = it },
            label = "Введите номер карты",
            title = "Номер карты",
            keyboardType = KeyboardType.Number
        )
        var exp by remember { mutableStateOf("") }
        TextField(
            text = exp,
            onTextChange = { if (it.isDigitsOnly() && it.length <= EXP) exp = it },
            label = "MM/YY",
            title = "Срок действия",
            keyboardType = KeyboardType.Number
        )
        var ver by remember { mutableStateOf("") }
        TextField(
            text = ver,
            onTextChange = { if (it.isDigitsOnly() && it.length <= VER) ver = it },
            label = "Введите CVV",
            title = "CVV",
            keyboardType = KeyboardType.Number
        )
        Text(
            text = stringResource(id = R.string.pickup),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.h5
        )
        val wrapper = ContextThemeWrapper(LocalContext.current, R.style.picker)
        AndroidView(factory = {
            val picker = DatePicker(wrapper)
            val calendar = GregorianCalendar(picker.year, picker.month, picker.dayOfMonth)
            picker.minDate = calendar.timeInMillis
            return@AndroidView picker
        })
        AndroidView(factory = {
            val picker = TimePicker(wrapper)
            picker.setIs24HourView(true)
            return@AndroidView picker
        })
        if (dialog.isNotEmpty()) {
            AlertDialog(
                onDismissRequest = { dialog = "" },
                confirmButton = {
                    TextButton(onClick = { dialog = "" }) {
                        Text(text = stringResource(android.R.string.ok))
                    }
                },
                title = { Text(text = "Внимание") },
                text = { Text(text = dialog) }
            )
        }
        var code by remember { mutableStateOf<UUID?>(null) }
        code?.let {
            AlertDialog(
                onDismissRequest = {},
                confirmButton = {
                    TextButton(onClick = { nav.popBackStack("restaurants", false) }) {
                        Text(text = "Выйти в главное меню")
                    }
                },
                title = { Text(text = "Сделайте скриншот или фото этого кода, чтобы потом предъявить его на кассе.") },
                text = {
                    Image(
                        bitmap = qr(it.toString()).asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        }
        val scope = rememberCoroutineScope()
        Button("Оплатить") {
            if (card.length < CARD) dialog = "Номер карты должен состоять из $CARD цифр"
            else if (exp.length < EXP) dialog = "Срок действия должен состоять из $EXP цифр"
            else if (ver.length < VER) dialog = "CVV должен состоять из $VER цифр"
            else scope.launch {
                val res = REAFoodService.instance.clearCart("Bearer $token")
                Log.d(TAG, "CheckoutScreen: $res")
                code = UUID.randomUUID()
            }
        }
    }
}

fun qr(text: String): Bitmap {
    val size = 512
    val code = QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, size, size)
    val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565)
    for (x in 0 until size) {
        for (y in 0 until size) {
            bitmap.setPixel(x, y, if (code.get(x, y)) Color.BLACK else Color.WHITE)
        }
    }
    return bitmap
}