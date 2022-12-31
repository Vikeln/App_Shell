package tech.nouveta.demo.ui.screens.splash

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import tech.nouveta.demo.R
import tech.nouveta.demo.composables.CentredImage
import tech.nouveta.demo.composables.SimpleText
import tech.nouveta.demo.ui.screens.destinations.HomeDestination
import tech.nouveta.demo.ui.screens.destinations.LoginScreenDestination
import timber.log.Timber


@Destination(start = true)
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator,
    viewModel: SplashScreenViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        withContext(Dispatchers.Main) {
            delay(3000)
            val result = viewModel.eventFlow.value
            Timber.d("vent ${result.isSuccessful}")

            if (result.isSuccessful) {
                Toast.makeText(context, result.successMessage, Toast.LENGTH_SHORT).show()
                navigator.popBackStack()
                navigator.navigate(HomeDestination)
            } else {
                Toast.makeText(context, result.successMessage, Toast.LENGTH_SHORT).show()
                navigator.popBackStack()
                navigator.navigate(LoginScreenDestination)
            }
        }
    }


    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        CentredImage(
            image = painterResource(id = R.drawable.logo),
            description = "logo",
            height = 200.dp,
            width = 200.dp
        )

        Spacer(modifier = Modifier.height(10.dp))

        SimpleText(
            color = Color.White,
            text = "WELCOME BACK",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}