package com.devmasum.specifiedcalldualsim;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;



public class MasumService implements MasumApi {

    private Context context;


    public MasumService(Context context) {
        this.context = context;
    }


    @Override
    public void send(String code, int sim) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "Call permission not granted", Toast.LENGTH_SHORT).show();
                return;
            }

            dialUp(code, sim);
        }
    }




    @RequiresApi(api = Build.VERSION_CODES.M)
    private void dialUp(String ussdPhoneNumber, int simSlot) {
        if (ussdPhoneNumber.isEmpty()) {
            Toast.makeText(context, "Bad ussd number", Toast.LENGTH_SHORT).show();
        } else {
            String uri = Uri.encode("#");
            if (uri != null) {
                ussdPhoneNumber = ussdPhoneNumber.replace("#", uri);
            }

            Uri uriPhone = Uri.parse("tel:" + ussdPhoneNumber);

            context.startActivity(getActionCallIntent(uriPhone, simSlot));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint({"MissingPermission"})
    private Intent getActionCallIntent(Uri uri, int simSlot) {
        String[] simSlotName = new String[]{"extra_asus_dial_use_dualsim", "com.android.phone.extra.slot", "slot", "simslot", "sim_slot", "subscription", "Subscription", "phone", "com.android.phone.DialingMode", "simSlot", "slot_id", "simId", "simnum", "phone_type", "slotId", "slotIdx"};
        Intent intent = new Intent("android.intent.action.CALL", uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("com.android.phone.force.slot", true);
        intent.putExtra("Cdma_Supp", true);
        String[] var5 = simSlotName;
        int var6 = simSlotName.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            String s = var5[var7];
            intent.putExtra(s, simSlot);
        }

        TelecomManager telecomManager = (TelecomManager)this.context.getSystemService(Context.TELECOM_SERVICE);
        if (telecomManager != null) {
            List<PhoneAccountHandle> phoneAccountHandleList = telecomManager.getCallCapablePhoneAccounts();
            if (phoneAccountHandleList != null && phoneAccountHandleList.size() > simSlot) {
                intent.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", (Parcelable)phoneAccountHandleList.get(simSlot));
            }
        }

        return intent;
    }



}
