# NightGuard

In our 24/7 society, cell phones, computers and other electronic devices play a large part in how we work, play and keep in touch. According to the National Sleep Foundation, about 90 percent of Americans regularly use an electronic device in the hour before they go to bed. But all that screen time may be harming our sleep.

So **NightGuard**, An Xposed module that would halt APPs in your sleeping time, comes. 😪

# Warning

Make sure you are able to recover device when anything unexpected take place!

Make sure you are able to recover device when anything unexpected take place!

Make sure you are able to recover device when anything unexpected take place!

# Basical Usage

1. Install the `release.apk` directly
2. Activate NightGuard, and check the target apps in Lsp manager
3. Adding time-spans in NightGuard to set the sleep time


## Enhance：

NightGuard is based on Lsposed, and an alternative enhanced with Magisk. So there 2 enhanced way for user.


1. Strill install directly

   1. Disable Lsp continuing notification and remove the Lsp manager shortcut.
   2. Check the `app-installer` of you system in Lsp manager. (When new xposed app installed, a Notification will be sent by Lsp)
   3. Check the `tell-dial` app of you system in Lsp manager, which is different from the `incall-app`. You are able to handle in call but couldont dial if you check. (Some special code can invoke the Lsp manager)
   4. Check any File-Manager that has the root access. (Prevent remove NightGuard in File-Manager)
2. Use magisk to install

   1. You can install the magisk module in release list, which help install NightGuard as system app. (Un-uninstallable)
   2. Remove Masigk manager, which dose not lose your root and could re-install anytime. (Prevent Magisk module be disabled)
   3. Check any File-Manager that has the root access. (Prevent remove Magisk module in File-Manager)
  


# Note
The hook code will release when there is no conspicuous bug, but not now.
