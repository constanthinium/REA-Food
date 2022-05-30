package ru.rea.food

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.core.content.edit
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import ru.rea.food.ui.screen.*
import ru.rea.food.ui.theme.REAFoodTheme
import ru.rea.food.vm.MainViewModel

@ExperimentalMaterialApi
@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImageFinder.preferences = getSharedPreferences(ImageFinder.TAG, Context.MODE_PRIVATE)
        setContent {
            REAFoodTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val nav = rememberNavController()
                    val prefs = getPreferences(Context.MODE_PRIVATE)
                    val token = prefs.getString("token", null)
                    val onLogIn: (String) -> Unit = { prefs.edit { putString("token", it) } }
                    val viewModel = viewModel<MainViewModel>()
                    if (viewModel.error) {
                        AlertDialog(
                            onDismissRequest = {},
                            confirmButton = {
                                TextButton(onClick = { viewModel.load() }) {
                                    Text(text = getString(R.string.retry))
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = { finishAffinity() }) {
                                    Text(text = getString(R.string.exit))
                                }
                            },
                            title = { Text(getString(R.string.error)) },
                            text = { Text(text = getString(R.string.internet)) })
                    }
                    NavHost(
                        navController = nav,
                        startDestination = if (token == null) "onboarding" else "restaurants",
                    ) {
                        composable("onboarding") { OnboardingScreen(nav) }
                        composable("create") { CreateAccountScreen(nav, onLogIn) }
                        composable("login") { LogInScreen(nav, onLogIn) }
                        composable("forgot") { ForgotPasswordScreen { nav.navigate("reset") } }
                        composable("reset") { ResetPasswordScreen() }
                        composable(
                            route = "menu/{placeId}",
                            arguments = listOf(navArgument("placeId") { type = NavType.IntType })
                        ) {
                            MenuScreen(it.arguments!!.getInt("placeId"), token!!, nav)
                        }
                        composable("restaurants") { RestaurantsScreen(nav, viewModel) }
                        composable("appMenu") { AppMenuScreen(nav, prefs, viewModel, token!!) }
                        composable("profile") {
                            ProfileScreen(token!!, viewModel) { nav.popBackStack() }
                        }
                    }
                }
            }
        }
    }
}