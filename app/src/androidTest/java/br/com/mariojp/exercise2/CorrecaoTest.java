package br.com.mariojp.exercise2;

import android.content.Context;
import android.content.pm.ActivityInfo;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import androidx.test.platform.app.InstrumentationRegistry;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.action.ViewActions.*;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;



/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class CorrecaoTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("br.com.mariojp.exercise2", appContext.getPackageName());
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);


    @Test
    public void checaValoresIniciaisDaPrimeiraTela() {
        onView(withId(R.id.textView))
                .check(matches(withText("Oi!")));
        onView(withId(R.id.btnTrocar))
                .check(matches(withText("Trocar usuário")));
    }

    @Test
    public void checaValoresIniciaisDaSegundaTela() {
        onView(withId(R.id.btnTrocar))
                .perform(click());

        onView(withId(R.id.editTextUserName))
                .check(matches(withText("")));


        onView(withId(R.id.btnConfirmar))
                .check(matches(withText("Confirmar")));

        onView(withId(R.id.btnCancelar))
                .check(matches(withText("Cancelar")));
    }

    @Test
    public void checaMensagemAoTrocarNome() {
        onView(withId(R.id.btnTrocar))
                .perform(click());

        onView(withId(R.id.editTextUserName))
                .perform(typeText("Sicrano"));

        onView(withId(R.id.editTextUserName)).perform(ViewActions.closeSoftKeyboard());


        onView(withId(R.id.btnConfirmar))
                .perform(click());

        onView(withId(R.id.textView))
                .check(matches(withText("Oi, Sicrano!")));
    }

    @Test
    public void checaSeMensagemNaoMudaAoCancelarATrocaDeNome() {
        onView(withId(R.id.btnTrocar))
                .perform(click());

        onView(withId(R.id.editTextUserName))
                .perform(typeText("Sicrano"));

        onView(withId(R.id.btnCancelar))
                .perform(click());

        onView(withId(R.id.textView))
                .check(matches(withText("Oi!")));
    }

    @Test
    public void checaSeMensagemNaoMudaAoCancelarATrocaDeNomePeloBotaoBack() {
        onView(withId(R.id.btnTrocar))
                .perform(click());

        onView(withId(R.id.editTextUserName))
                .perform(typeText("Sicrano"));

        onView(withId(R.id.editTextUserName)).perform(ViewActions.closeSoftKeyboard());


        Espresso.pressBack();

        onView(withId(R.id.textView))
                .check(matches(withText("Oi!")));
    }

    @Test
    public void checaSeCaixaDeTextoMostraUsuarioAtual() {
        onView(withId(R.id.btnTrocar))
                .perform(click());

        onView(withId(R.id.editTextUserName))
                .perform(typeText("Beltrano"));

        onView(withId(R.id.btnConfirmar))
                .perform(click());

        onView(withId(R.id.btnTrocar))
                .perform(click());

        onView(withId(R.id.editTextUserName))
                .check(matches(withText("Beltrano")));
    }

    @Test
    public void checaSeStringVaziaResultaEmNaoTerUsuarioAtual() {
        onView(withId(R.id.btnTrocar))
                .perform(click());

        onView(withId(R.id.editTextUserName))
                .perform(typeText("Beltrano"));

        onView(withId(R.id.btnConfirmar))
                .perform(click());

        onView(withId(R.id.btnTrocar))
                .perform(click());

        onView(withId(R.id.editTextUserName))
                .perform(clearText());

        onView(withId(R.id.btnConfirmar))
                .perform(click());

        onView(withId(R.id.textView))
                .check(matches(withText("Oi!")));
    }

    @Test
    public void checaSeMensagemSeMantemAposRotacao() {
        onView(withId(R.id.btnTrocar))
                .perform(click());

        onView(withId(R.id.editTextUserName))
                .perform(typeText("Beltrano"));

        onView(withId(R.id.editTextUserName)).perform(ViewActions.closeSoftKeyboard());


        onView(withId(R.id.btnConfirmar))
                .perform(click());

        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        onView(withId(R.id.textView))
                .check(matches(withText("Oi, Beltrano!")));
    }

    @Test
    public void checaSeUsuarioAtualSeMantemAposRotacao() {
        onView(withId(R.id.btnTrocar))
                .perform(click());

        onView(withId(R.id.editTextUserName))
                .perform(typeText("Beltrano"));

        onView(withId(R.id.editTextUserName)).perform(ViewActions.closeSoftKeyboard());


        onView(withId(R.id.btnConfirmar))
                .perform(click());

        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        onView(withId(R.id.btnTrocar))
                .perform(click());

        onView(withId(R.id.editTextUserName))
                .check(matches(withText("Beltrano")));
    }


}
