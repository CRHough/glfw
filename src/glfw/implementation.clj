(ns glfw.implementation
  (:refer-clojure :exclude [repeat])
  (:import org.lwjgl.system.MemoryUtil
           org.lwjgl.PointerBuffer
           [org.lwjgl.vulkan
             VkAllocationCallbacks
             VkInstance
             VkAllocationFunctionI
             VkReallocationFunctionI
             VkFreeFunctionI
             VkInternalAllocationNotificationI
             VkInternalFreeNotificationI]
           [org.lwjgl.glfw
             GLFW
             GLFWNativeCocoa
             GLFWNativeCocoa$Functions
             GLFWNativeEGL
             GLFWNativeEGL$Functions
             GLFWNativeGLX
             GLFWNativeGLX$Functions
             GLFWNativeNSGL
             GLFWNativeNSGL$Functions
             GLFWNativeWGL
             GLFWNativeWGL$Functions
             GLFWNativeWin32
             GLFWNativeWin32$Functions
             GLFWNativeX11
             GLFWNativeX11$Functions
             GLFWVulkan
             GLFWVulkan$Functions
             GLFWGammaRamp
             GLFWGammaRamp$Buffer
             GLFWImage
             GLFWImage$Buffer
             GLFWVidMode
             GLFWVidMode$Buffer
             GLFWCharCallbackI
             GLFWCharModsCallbackI
             GLFWCursorEnterCallbackI
             GLFWCursorPosCallbackI
             GLFWDropCallbackI
             GLFWErrorCallbackI
             GLFWFramebufferSizeCallbackI
             GLFWJoystickCallbackI
             GLFWKeyCallbackI
             GLFWMonitorCallbackI
             GLFWMouseButtonCallbackI
             GLFWScrollCallbackI
             GLFWWindowCloseCallbackI
             GLFWWindowFocusCallbackI
             GLFWWindowIconifyCallbackI
             GLFWWindowMaximizeCallbackI
             GLFWWindowPosCallbackI
             GLFWWindowRefreshCallbackI
             GLFWWindowSizeCallbackI]))

;; TODO cachify fn->x and x->fn with a shared cache (per fn) to eliminate degenerative behavior

(def version-major GLFW/GLFW_VERSION_MAJOR)
(def version-minor GLFW/GLFW_VERSION_MINOR)
(def version-revision GLFW/GLFW_VERSION_REVISION)
(def TRUE GLFW/GLFW_TRUE)
(def FALSE GLFW/GLFW_FALSE)
(def release GLFW/GLFW_RELEASE)
(def press GLFW/GLFW_PRESS)
(def repeat GLFW/GLFW_REPEAT)
(def hat-centered GLFW/GLFW_HAT_CENTERED)
(def hat-up GLFW/GLFW_HAT_UP)
(def hat-right GLFW/GLFW_HAT_RIGHT)
(def hat-down GLFW/GLFW_HAT_DOWN)
(def hat-left GLFW/GLFW_HAT_LEFT)
(def hat-right-up GLFW/GLFW_HAT_RIGHT_UP)
(def hat-right-down GLFW/GLFW_HAT_RIGHT_DOWN)
(def hat-left-up GLFW/GLFW_HAT_LEFT_UP)
(def hat-left-down GLFW/GLFW_HAT_LEFT_DOWN)
(def key-unknown GLFW/GLFW_KEY_UNKNOWN)
(def key-space GLFW/GLFW_KEY_SPACE)
(def key-apostrophe GLFW/GLFW_KEY_APOSTROPHE)
(def key-comma GLFW/GLFW_KEY_COMMA)
(def key-minus GLFW/GLFW_KEY_MINUS)
(def key-period GLFW/GLFW_KEY_PERIOD)
(def key-slash GLFW/GLFW_KEY_SLASH)
(def key-0 GLFW/GLFW_KEY_0)
(def key-1 GLFW/GLFW_KEY_1)
(def key-2 GLFW/GLFW_KEY_2)
(def key-3 GLFW/GLFW_KEY_3)
(def key-4 GLFW/GLFW_KEY_4)
(def key-5 GLFW/GLFW_KEY_5)
(def key-6 GLFW/GLFW_KEY_6)
(def key-7 GLFW/GLFW_KEY_7)
(def key-8 GLFW/GLFW_KEY_8)
(def key-9 GLFW/GLFW_KEY_9)
(def key-semicolon GLFW/GLFW_KEY_SEMICOLON)
(def key-equal GLFW/GLFW_KEY_EQUAL)
(def key-a GLFW/GLFW_KEY_A)
(def key-b GLFW/GLFW_KEY_B)
(def key-c GLFW/GLFW_KEY_C)
(def key-d GLFW/GLFW_KEY_D)
(def key-e GLFW/GLFW_KEY_E)
(def key-f GLFW/GLFW_KEY_F)
(def key-g GLFW/GLFW_KEY_G)
(def key-h GLFW/GLFW_KEY_H)
(def key-i GLFW/GLFW_KEY_I)
(def key-j GLFW/GLFW_KEY_J)
(def key-k GLFW/GLFW_KEY_K)
(def key-l GLFW/GLFW_KEY_L)
(def key-m GLFW/GLFW_KEY_M)
(def key-n GLFW/GLFW_KEY_N)
(def key-o GLFW/GLFW_KEY_O)
(def key-p GLFW/GLFW_KEY_P)
(def key-q GLFW/GLFW_KEY_Q)
(def key-r GLFW/GLFW_KEY_R)
(def key-s GLFW/GLFW_KEY_S)
(def key-t GLFW/GLFW_KEY_T)
(def key-u GLFW/GLFW_KEY_U)
(def key-v GLFW/GLFW_KEY_V)
(def key-w GLFW/GLFW_KEY_W)
(def key-x GLFW/GLFW_KEY_X)
(def key-y GLFW/GLFW_KEY_Y)
(def key-z GLFW/GLFW_KEY_Z)
(def key-left-bracket GLFW/GLFW_KEY_LEFT_BRACKET)
(def key-backslash GLFW/GLFW_KEY_BACKSLASH)
(def key-right-bracket GLFW/GLFW_KEY_RIGHT_BRACKET)
(def key-grave-accent GLFW/GLFW_KEY_GRAVE_ACCENT)
(def key-world-1 GLFW/GLFW_KEY_WORLD_1)
(def key-world-2 GLFW/GLFW_KEY_WORLD_2)
(def key-escape GLFW/GLFW_KEY_ESCAPE)
(def key-enter GLFW/GLFW_KEY_ENTER)
(def key-tab GLFW/GLFW_KEY_TAB)
(def key-backspace GLFW/GLFW_KEY_BACKSPACE)
(def key-insert GLFW/GLFW_KEY_INSERT)
(def key-delete GLFW/GLFW_KEY_DELETE)
(def key-right GLFW/GLFW_KEY_RIGHT)
(def key-left GLFW/GLFW_KEY_LEFT)
(def key-down GLFW/GLFW_KEY_DOWN)
(def key-up GLFW/GLFW_KEY_UP)
(def key-page-up GLFW/GLFW_KEY_PAGE_UP)
(def key-page-down GLFW/GLFW_KEY_PAGE_DOWN)
(def key-home GLFW/GLFW_KEY_HOME)
(def key-end GLFW/GLFW_KEY_END)
(def key-caps-lock GLFW/GLFW_KEY_CAPS_LOCK)
(def key-scroll-lock GLFW/GLFW_KEY_SCROLL_LOCK)
(def key-num-lock GLFW/GLFW_KEY_NUM_LOCK)
(def key-print-screen GLFW/GLFW_KEY_PRINT_SCREEN)
(def key-pause GLFW/GLFW_KEY_PAUSE)
(def key-f1 GLFW/GLFW_KEY_F1)
(def key-f2 GLFW/GLFW_KEY_F2)
(def key-f3 GLFW/GLFW_KEY_F3)
(def key-f4 GLFW/GLFW_KEY_F4)
(def key-f5 GLFW/GLFW_KEY_F5)
(def key-f6 GLFW/GLFW_KEY_F6)
(def key-f7 GLFW/GLFW_KEY_F7)
(def key-f8 GLFW/GLFW_KEY_F8)
(def key-f9 GLFW/GLFW_KEY_F9)
(def key-f10 GLFW/GLFW_KEY_F10)
(def key-f11 GLFW/GLFW_KEY_F11)
(def key-f12 GLFW/GLFW_KEY_F12)
(def key-f13 GLFW/GLFW_KEY_F13)
(def key-f14 GLFW/GLFW_KEY_F14)
(def key-f15 GLFW/GLFW_KEY_F15)
(def key-f16 GLFW/GLFW_KEY_F16)
(def key-f17 GLFW/GLFW_KEY_F17)
(def key-f18 GLFW/GLFW_KEY_F18)
(def key-f19 GLFW/GLFW_KEY_F19)
(def key-f20 GLFW/GLFW_KEY_F20)
(def key-f21 GLFW/GLFW_KEY_F21)
(def key-f22 GLFW/GLFW_KEY_F22)
(def key-f23 GLFW/GLFW_KEY_F23)
(def key-f24 GLFW/GLFW_KEY_F24)
(def key-f25 GLFW/GLFW_KEY_F25)
(def key-kp-0 GLFW/GLFW_KEY_KP_0)
(def key-kp-1 GLFW/GLFW_KEY_KP_1)
(def key-kp-2 GLFW/GLFW_KEY_KP_2)
(def key-kp-3 GLFW/GLFW_KEY_KP_3)
(def key-kp-4 GLFW/GLFW_KEY_KP_4)
(def key-kp-5 GLFW/GLFW_KEY_KP_5)
(def key-kp-6 GLFW/GLFW_KEY_KP_6)
(def key-kp-7 GLFW/GLFW_KEY_KP_7)
(def key-kp-8 GLFW/GLFW_KEY_KP_8)
(def key-kp-9 GLFW/GLFW_KEY_KP_9)
(def key-kp-decimal GLFW/GLFW_KEY_KP_DECIMAL)
(def key-kp-divide GLFW/GLFW_KEY_KP_DIVIDE)
(def key-kp-multiply GLFW/GLFW_KEY_KP_MULTIPLY)
(def key-kp-subtract GLFW/GLFW_KEY_KP_SUBTRACT)
(def key-kp-add GLFW/GLFW_KEY_KP_ADD)
(def key-kp-enter GLFW/GLFW_KEY_KP_ENTER)
(def key-kp-equal GLFW/GLFW_KEY_KP_EQUAL)
(def key-left-shift GLFW/GLFW_KEY_LEFT_SHIFT)
(def key-left-control GLFW/GLFW_KEY_LEFT_CONTROL)
(def key-left-alt GLFW/GLFW_KEY_LEFT_ALT)
(def key-left-super GLFW/GLFW_KEY_LEFT_SUPER)
(def key-right-shift GLFW/GLFW_KEY_RIGHT_SHIFT)
(def key-right-control GLFW/GLFW_KEY_RIGHT_CONTROL)
(def key-right-alt GLFW/GLFW_KEY_RIGHT_ALT)
(def key-right-super GLFW/GLFW_KEY_RIGHT_SUPER)
(def key-menu GLFW/GLFW_KEY_MENU)
(def mod-shift GLFW/GLFW_MOD_SHIFT)
(def mod-control GLFW/GLFW_MOD_CONTROL)
(def mod-alt GLFW/GLFW_MOD_ALT)
(def mod-super GLFW/GLFW_MOD_SUPER)
(def mouse-button-1 GLFW/GLFW_MOUSE_BUTTON_1)
(def mouse-button-2 GLFW/GLFW_MOUSE_BUTTON_2)
(def mouse-button-3 GLFW/GLFW_MOUSE_BUTTON_3)
(def mouse-button-4 GLFW/GLFW_MOUSE_BUTTON_4)
(def mouse-button-5 GLFW/GLFW_MOUSE_BUTTON_5)
(def mouse-button-6 GLFW/GLFW_MOUSE_BUTTON_6)
(def mouse-button-7 GLFW/GLFW_MOUSE_BUTTON_7)
(def mouse-button-8 GLFW/GLFW_MOUSE_BUTTON_8)
(def mouse-button-left GLFW/GLFW_MOUSE_BUTTON_LEFT)
(def mouse-button-right GLFW/GLFW_MOUSE_BUTTON_RIGHT)
(def mouse-button-middle GLFW/GLFW_MOUSE_BUTTON_MIDDLE)
(def joystick-1 GLFW/GLFW_JOYSTICK_1)
(def joystick-2 GLFW/GLFW_JOYSTICK_2)
(def joystick-3 GLFW/GLFW_JOYSTICK_3)
(def joystick-4 GLFW/GLFW_JOYSTICK_4)
(def joystick-5 GLFW/GLFW_JOYSTICK_5)
(def joystick-6 GLFW/GLFW_JOYSTICK_6)
(def joystick-7 GLFW/GLFW_JOYSTICK_7)
(def joystick-8 GLFW/GLFW_JOYSTICK_8)
(def joystick-9 GLFW/GLFW_JOYSTICK_9)
(def joystick-10 GLFW/GLFW_JOYSTICK_10)
(def joystick-11 GLFW/GLFW_JOYSTICK_11)
(def joystick-12 GLFW/GLFW_JOYSTICK_12)
(def joystick-13 GLFW/GLFW_JOYSTICK_13)
(def joystick-14 GLFW/GLFW_JOYSTICK_14)
(def joystick-15 GLFW/GLFW_JOYSTICK_15)
(def joystick-16 GLFW/GLFW_JOYSTICK_16)
(def no-error GLFW/GLFW_NO_ERROR)
(def not-initialized GLFW/GLFW_NOT_INITIALIZED)
(def no-current-context GLFW/GLFW_NO_CURRENT_CONTEXT)
(def invalid-enum GLFW/GLFW_INVALID_ENUM)
(def invalid-value GLFW/GLFW_INVALID_VALUE)
(def out-of-memory GLFW/GLFW_OUT_OF_MEMORY)
(def api-unavailable GLFW/GLFW_API_UNAVAILABLE)
(def version-unavailable GLFW/GLFW_VERSION_UNAVAILABLE)
(def platform-error GLFW/GLFW_PLATFORM_ERROR)
(def format-unavailable GLFW/GLFW_FORMAT_UNAVAILABLE)
(def no-window-context GLFW/GLFW_NO_WINDOW_CONTEXT)
(def focused GLFW/GLFW_FOCUSED)
(def iconified GLFW/GLFW_ICONIFIED)
(def resizable GLFW/GLFW_RESIZABLE)
(def visible GLFW/GLFW_VISIBLE)
(def decorated GLFW/GLFW_DECORATED)
(def auto-iconify GLFW/GLFW_AUTO_ICONIFY)
(def floating GLFW/GLFW_FLOATING)
(def maximized GLFW/GLFW_MAXIMIZED)
(def center-cursor GLFW/GLFW_CENTER_CURSOR)
(def cursor GLFW/GLFW_CURSOR)
(def sticky-keys GLFW/GLFW_STICKY_KEYS)
(def sticky-mouse-buttons GLFW/GLFW_STICKY_MOUSE_BUTTONS)
(def cursor-normal GLFW/GLFW_CURSOR_NORMAL)
(def cursor-hidden GLFW/GLFW_CURSOR_HIDDEN)
(def cursor-disabled GLFW/GLFW_CURSOR_DISABLED)
(def arrow-cursor GLFW/GLFW_ARROW_CURSOR)
(def ibeam-cursor GLFW/GLFW_IBEAM_CURSOR)
(def crosshair-cursor GLFW/GLFW_CROSSHAIR_CURSOR)
(def hand-cursor GLFW/GLFW_HAND_CURSOR)
(def hresize-cursor GLFW/GLFW_HRESIZE_CURSOR)
(def vresize-cursor GLFW/GLFW_VRESIZE_CURSOR)
(def connected GLFW/GLFW_CONNECTED)
(def disconnected GLFW/GLFW_DISCONNECTED)
(def joystick-hat-buttons GLFW/GLFW_JOYSTICK_HAT_BUTTONS)
(def cocoa-chdir-resources GLFW/GLFW_COCOA_CHDIR_RESOURCES)
(def cocoa-menubar GLFW/GLFW_COCOA_MENUBAR)
(def dont-care GLFW/GLFW_DONT_CARE)
(def red-bits GLFW/GLFW_RED_BITS)
(def green-bits GLFW/GLFW_GREEN_BITS)
(def blue-bits GLFW/GLFW_BLUE_BITS)
(def alpha-bits GLFW/GLFW_ALPHA_BITS)
(def depth-bits GLFW/GLFW_DEPTH_BITS)
(def stencil-bits GLFW/GLFW_STENCIL_BITS)
(def accum-red-bits GLFW/GLFW_ACCUM_RED_BITS)
(def accum-green-bits GLFW/GLFW_ACCUM_GREEN_BITS)
(def accum-blue-bits GLFW/GLFW_ACCUM_BLUE_BITS)
(def accum-alpha-bits GLFW/GLFW_ACCUM_ALPHA_BITS)
(def aux-buffers GLFW/GLFW_AUX_BUFFERS)
(def stereo GLFW/GLFW_STEREO)
(def samples GLFW/GLFW_SAMPLES)
(def srgb-capable GLFW/GLFW_SRGB_CAPABLE)
(def refresh-rate GLFW/GLFW_REFRESH_RATE)
(def doublebuffer GLFW/GLFW_DOUBLEBUFFER)
(def client-api GLFW/GLFW_CLIENT_API)
(def context-version-major GLFW/GLFW_CONTEXT_VERSION_MAJOR)
(def context-version-minor GLFW/GLFW_CONTEXT_VERSION_MINOR)
(def context-revision GLFW/GLFW_CONTEXT_REVISION)
(def context-robustness GLFW/GLFW_CONTEXT_ROBUSTNESS)
(def opengl-forward-compat GLFW/GLFW_OPENGL_FORWARD_COMPAT)
(def opengl-debug-context GLFW/GLFW_OPENGL_DEBUG_CONTEXT)
(def opengl-profile GLFW/GLFW_OPENGL_PROFILE)
(def context-release-behavior GLFW/GLFW_CONTEXT_RELEASE_BEHAVIOR)
(def context-no-error GLFW/GLFW_CONTEXT_NO_ERROR)
(def context-creation-api GLFW/GLFW_CONTEXT_CREATION_API)
(def cocoa-retina-framebuffer GLFW/GLFW_COCOA_RETINA_FRAMEBUFFER)
(def cocoa-frame-autosave GLFW/GLFW_COCOA_FRAME_AUTOSAVE)
(def cocoa-graphics-switching GLFW/GLFW_COCOA_GRAPHICS_SWITCHING)
(def no-api GLFW/GLFW_NO_API)
(def opengl-api GLFW/GLFW_OPENGL_API)
(def opengl-es-api GLFW/GLFW_OPENGL_ES_API)
(def no-robustness GLFW/GLFW_NO_ROBUSTNESS)
(def no-reset-notification GLFW/GLFW_NO_RESET_NOTIFICATION)
(def lose-context-on-reset GLFW/GLFW_LOSE_CONTEXT_ON_RESET)
(def opengl-any-profile GLFW/GLFW_OPENGL_ANY_PROFILE)
(def opengl-core-profile GLFW/GLFW_OPENGL_CORE_PROFILE)
(def opengl-compat-profile GLFW/GLFW_OPENGL_COMPAT_PROFILE)
(def any-release-behavior GLFW/GLFW_ANY_RELEASE_BEHAVIOR)
(def release-behavior-flush GLFW/GLFW_RELEASE_BEHAVIOR_FLUSH)
(def release-behavior-none GLFW/GLFW_RELEASE_BEHAVIOR_NONE)
(def native-context-api GLFW/GLFW_NATIVE_CONTEXT_API)
(def egl-context-api GLFW/GLFW_EGL_CONTEXT_API)
(def osmesa-context-api GLFW/GLFW_OSMESA_CONTEXT_API)

(defn null->nil [val]
  (if (= 0 val) nil val))

(defn nil->null [val]
  (if (nil? val) 0 val))

(defn boolean->int [val]
  (cond (= val true) 1 (= val false) 0 :default val))

(defn int->boolean [val]
  (cond (= val 0) false (= val 1) true :default val))

(defn fn->GLFWCharCallback ^GLFWCharCallbackI [f]
  (reify GLFWCharCallbackI
    (invoke [_ window codepoint]
      (f (long window) (int codepoint)))))
(defn GLFWCharCallback->fn [^GLFWCharCallbackI this]
  (fn [window codepoint]
    (.invoke this (long window) (int codepoint))))

(defn fn->GLFWCharModsCallback ^GLFWCharModsCallbackI [f]
  (reify GLFWCharModsCallbackI
    (invoke [_ window codepoint mods]
      (f (long window)
        (int codepoint)
        (get
          {mod-shift #{:glfw.core/mod-shift}
           mod-control #{:glfw.core/mod-control}
           (bit-or mod-shift mod-control) #{:glfw.core/mod-control :glfw.core/mod-shift}
           mod-alt #{:glfw.core/mod-alt}
           (bit-or mod-alt mod-shift) #{:glfw.core/mod-alt :glfw.core/mod-shift}
           (bit-or mod-alt mod-control) #{:glfw.core/mod-alt :glfw.core/mod-control}
           (bit-or mod-alt mod-shift mod-control) #{:glfw.core/mod-alt :glfw.core/mod-shift :glfw.core/mod-control}
           mod-super #{:glfw.core/mod-super}
           (bit-or mod-super mod-shift) #{:glfw.core/mod-super :glfw.core/mod-shift}
           (bit-or mod-super mod-control) #{:glfw.core/mod-super :glfw.core/mod-control}
           (bit-or mod-super mod-shift mod-control) #{:glfw.core/mod-super :glfw.core/mod-shift :glfw.core/mod-control}
           (bit-or mod-super mod-alt) #{:glfw.core/mod-super :glfw.core/mod-alt}
           (bit-or mod-super mod-alt mod-shift) #{:glfw.core/mod-super :glfw.core/mod-alt :glfw.core/mod-shift}
           (bit-or mod-super mod-alt mod-control) #{:glfw.core/mod-super :glfw.core/mod-alt :glfw.core/mod-control}
           (bit-or mod-super mod-alt mod-shift mod-control) #{:glfw.core/mod-super :glfw.core/mod-alt :glfw.core/mod-shift :glfw.core/mod-control}}
          mods
          mods)))))
(defn GLFWCharModsCallback->fn [^GLFWCharModsCallbackI this]
  (fn [window codepoint mods]
    (.invoke this
      (long window)
      (int codepoint)
      (int
        (case mods
          #{:glfw.core/mod-shift} mod-shift
          #{:glfw.core/mod-control} mod-control
          #{:glfw.core/mod-control :glfw.core/mod-shift} (bit-or mod-shift mod-control)
          #{:glfw.core/mod-alt} mod-alt
          #{:glfw.core/mod-alt :glfw.core/mod-shift} (bit-or mod-alt mod-shift)
          #{:glfw.core/mod-alt :glfw.core/mod-control} (bit-or mod-alt mod-control)
          #{:glfw.core/mod-alt :glfw.core/mod-shift :glfw.core/mod-control} (bit-or mod-alt mod-shift mod-control)
          #{:glfw.core/mod-super} mod-super
          #{:glfw.core/mod-super :glfw.core/mod-shift} (bit-or mod-super mod-shift)
          #{:glfw.core/mod-super :glfw.core/mod-control} (bit-or mod-super mod-control)
          #{:glfw.core/mod-super :glfw.core/mod-shift :glfw.core/mod-control} (bit-or mod-super mod-shift mod-control)
          #{:glfw.core/mod-super :glfw.core/mod-alt} (bit-or mod-super mod-alt)
          #{:glfw.core/mod-super :glfw.core/mod-alt :glfw.core/mod-shift} (bit-or mod-super mod-alt mod-shift)
          #{:glfw.core/mod-super :glfw.core/mod-alt :glfw.core/mod-control} (bit-or mod-super mod-alt mod-control)
          #{:glfw.core/mod-super :glfw.core/mod-alt :glfw.core/mod-shift :glfw.core/mod-control} (bit-or mod-super mod-alt mod-shift mod-control)
          mods)))))

(defn fn->GLFWCursorEnterCallback ^GLFWCursorEnterCallbackI [f]
  (reify GLFWCursorEnterCallbackI
    (invoke [_ window entered]
      (f (long window)
        (case entered
          :glfw.core/true true
          :glfw.core/false false
          entered)))))
(defn GLFWCursorEnterCallback->fn [^GLFWCursorEnterCallbackI this]
  (fn [window entered]
    (.invoke this
      (long window)
      (case entered
        true :glfw.core/true
        false :glfw.core/false
        entered))))

(defn fn->GLFWCursorPosCallback ^GLFWCursorPosCallbackI [f]
  (reify GLFWCursorPosCallbackI
    (invoke [_ window x y]
      (f (long window) (double x) (double y)))))
(defn GLFWCursorPosCallback->fn [^GLFWCursorPosCallbackI this]
  (fn [window x y]
    (.invoke this (long window) (double x) (double y))))

;; TODO abstract over names so that it doesn't have to be a native pointer to memory
(defn fn->GLFWDropCallback ^GLFWDropCallbackI [f]
  (reify GLFWDropCallbackI
    (invoke [_ window count names]
      (f (long window) (int count) (long names)))))
(defn GLFWDropCallback->fn [^GLFWDropCallbackI this]
  (fn [window count names]
    (.invoke this (long window) (int count) (long names))))

(defn fn->GLFWErrorCallback ^GLFWErrorCallbackI [f]
  (reify GLFWErrorCallbackI
    (invoke [_ error description]
      (f
        (get
          {not-initialized :glfw.core/not-initialized
           no-current-context :glfw.core/no-current-context
           invalid-enum :glfw.core/invalid-enum
           invalid-value :glfw.core/invalid-value
           out-of-memory :glfw.core/out-of-memory
           api-unavailable :glfw.core/api-unavailable
           version-unavailable :glfw.core/version-unavailable
           platform-error :glfw.core/platform-error
           format-unavailable :glfw.core/format-unavailable}
          error
          error)
        description))))
(defn GLFWErrorCallback->fn [^GLFWErrorCallbackI this]
  (fn [error description]
    (.invoke this
      (long error)
      (long
        (case description
          :glfw.core/not-initialized not-initialized
          :glfw.core/no-current-context no-current-context
          :glfw.core/invalid-enum invalid-enum
          :glfw.core/invalid-value invalid-value
          :glfw.core/out-of-memory out-of-memory
          :glfw.core/api-unavailable api-unavailable
          :glfw.core/version-unavailable version-unavailable
          :glfw.core/platform-error platform-error
          :glfw.core/format-unavailable format-unavailable
          description)))))

(defn fn->GLFWFramebufferSizeCallback ^GLFWFramebufferSizeCallbackI [f]
  (reify GLFWFramebufferSizeCallbackI
    (invoke [_ window width height]
      (f (long window) (int width) (int height)))))
(defn GLFWFramebufferSizeCallback->fn [^GLFWFramebufferSizeCallbackI this]
  (fn [window width height]
    (.invoke this (long window) (int width) (int height))))

(defn fn->GLFWJoystickCallback ^GLFWJoystickCallbackI [f]
  (reify GLFWJoystickCallbackI
    (invoke [_ jid event]
      (f (int jid)
        (get
          {connected :glfw.core/connected
           disconnected :glfw.core/disconnected}
          event
          event)))))
(defn GLFWJoystickCallback->fn [^GLFWJoystickCallbackI this]
  (fn [jid event]
    (.invoke this (int jid)
      (case event
        :glfw.core/connected connected
        :glfw.core/disconnected disconnected
        event))))

(defn fn->GLFWKeyCallback ^GLFWKeyCallbackI [f]
  (reify GLFWKeyCallbackI
    (invoke [_ window key scancode action mods]
      (f (long window)
        (get
          {key-unknown :glfw.core/key-unknown
           key-space :glfw.core/key-space
           key-apostrophe :glfw.core/key-apostrophe
           key-comma :glfw.core/key-comma
           key-minus :glfw.core/key-minus
           key-period :glfw.core/key-period
           key-slash :glfw.core/key-slash
           key-0 :glfw.core/key-0
           key-1 :glfw.core/key-1
           key-2 :glfw.core/key-2
           key-3 :glfw.core/key-3
           key-4 :glfw.core/key-4
           key-5 :glfw.core/key-5
           key-6 :glfw.core/key-6
           key-7 :glfw.core/key-7
           key-8 :glfw.core/key-8
           key-9 :glfw.core/key-9
           key-semicolon :glfw.core/key-semicolon
           key-equal :glfw.core/key-equal
           key-a :glfw.core/key-a
           key-b :glfw.core/key-b
           key-c :glfw.core/key-c
           key-d :glfw.core/key-d
           key-e :glfw.core/key-e
           key-f :glfw.core/key-f
           key-g :glfw.core/key-g
           key-h :glfw.core/key-h
           key-i :glfw.core/key-i
           key-j :glfw.core/key-j
           key-k :glfw.core/key-k
           key-l :glfw.core/key-l
           key-m :glfw.core/key-m
           key-n :glfw.core/key-n
           key-o :glfw.core/key-o
           key-p :glfw.core/key-p
           key-q :glfw.core/key-q
           key-r :glfw.core/key-r
           key-s :glfw.core/key-s
           key-t :glfw.core/key-t
           key-u :glfw.core/key-u
           key-v :glfw.core/key-v
           key-w :glfw.core/key-w
           key-x :glfw.core/key-x
           key-y :glfw.core/key-y
           key-z :glfw.core/key-z
           key-left-bracket :glfw.core/key-left-bracket
           key-backslash :glfw.core/key-backslash
           key-right-bracket :glfw.core/key-right-bracket
           key-grave-accent :glfw.core/key-grave-accent
           key-world-1 :glfw.core/key-world-1
           key-world-2 :glfw.core/key-world-2
           key-escape :glfw.core/key-escape
           key-enter :glfw.core/key-enter
           key-tab :glfw.core/key-tab
           key-backspace :glfw.core/key-backspace
           key-insert :glfw.core/key-insert
           key-delete :glfw.core/key-delete
           key-right :glfw.core/key-right
           key-left :glfw.core/key-left
           key-down :glfw.core/key-down
           key-up :glfw.core/key-up
           key-page-up :glfw.core/key-page-up
           key-page-down :glfw.core/key-page-down
           key-home :glfw.core/key-home
           key-end :glfw.core/key-end
           key-caps-lock :glfw.core/key-caps-lock
           key-scroll-lock :glfw.core/key-scroll-lock
           key-num-lock :glfw.core/key-num-lock
           key-print-screen :glfw.core/key-print-screen
           key-pause :glfw.core/key-pause
           key-f1 :glfw.core/key-f1
           key-f2 :glfw.core/key-f2
           key-f3 :glfw.core/key-f3
           key-f4 :glfw.core/key-f4
           key-f5 :glfw.core/key-f5
           key-f6 :glfw.core/key-f6
           key-f7 :glfw.core/key-f7
           key-f8 :glfw.core/key-f8
           key-f9 :glfw.core/key-f9
           key-f10 :glfw.core/key-f10
           key-f11 :glfw.core/key-f11
           key-f12 :glfw.core/key-f12
           key-f13 :glfw.core/key-f13
           key-f14 :glfw.core/key-f14
           key-f15 :glfw.core/key-f15
           key-f16 :glfw.core/key-f16
           key-f17 :glfw.core/key-f17
           key-f18 :glfw.core/key-f18
           key-f19 :glfw.core/key-f19
           key-f20 :glfw.core/key-f20
           key-f21 :glfw.core/key-f21
           key-f22 :glfw.core/key-f22
           key-f23 :glfw.core/key-f23
           key-f24 :glfw.core/key-f24
           key-f25 :glfw.core/key-f25
           key-kp-0 :glfw.core/key-kp-0
           key-kp-1 :glfw.core/key-kp-1
           key-kp-2 :glfw.core/key-kp-2
           key-kp-3 :glfw.core/key-kp-3
           key-kp-4 :glfw.core/key-kp-4
           key-kp-5 :glfw.core/key-kp-5
           key-kp-6 :glfw.core/key-kp-6
           key-kp-7 :glfw.core/key-kp-7
           key-kp-8 :glfw.core/key-kp-8
           key-kp-9 :glfw.core/key-kp-9
           key-kp-decimal :glfw.core/key-kp-decimal
           key-kp-divide :glfw.core/key-kp-divide
           key-kp-multiply :glfw.core/key-kp-multiply
           key-kp-subtract :glfw.core/key-kp-subtract
           key-kp-add :glfw.core/key-kp-add
           key-kp-enter :glfw.core/key-kp-enter
           key-kp-equal :glfw.core/key-kp-equal
           key-left-shift :glfw.core/key-left-shift
           key-left-control :glfw.core/key-left-control
           key-left-alt :glfw.core/key-left-alt
           key-left-super :glfw.core/key-left-super
           key-right-shift :glfw.core/key-right-shift
           key-right-control :glfw.core/key-right-control
           key-right-alt :glfw.core/key-right-alt
           key-right-super :glfw.core/key-right-super
           key-menu :glfw.core/key-menu}
          key
          key)
        (int scancode)
        (get
          {press :glfw.core/press
           release :glfw.core/release
           repeat :glfw.core/repeat}
          action
          action)
        (get
          {mod-shift #{:glfw.core/mod-shift}
           mod-control #{:glfw.core/mod-control}
           (bit-or mod-shift mod-control) #{:glfw.core/mod-control :glfw.core/mod-shift}
           mod-alt #{:glfw.core/mod-alt}
           (bit-or mod-alt mod-shift) #{:glfw.core/mod-alt :glfw.core/mod-shift}
           (bit-or mod-alt mod-control) #{:glfw.core/mod-alt :glfw.core/mod-control}
           (bit-or mod-alt mod-shift mod-control) #{:glfw.core/mod-alt :glfw.core/mod-shift :glfw.core/mod-control}
           mod-super #{:glfw.core/mod-super}
           (bit-or mod-super mod-shift) #{:glfw.core/mod-super :glfw.core/mod-shift}
           (bit-or mod-super mod-control) #{:glfw.core/mod-super :glfw.core/mod-control}
           (bit-or mod-super mod-shift mod-control) #{:glfw.core/mod-super :glfw.core/mod-shift :glfw.core/mod-control}
           (bit-or mod-super mod-alt) #{:glfw.core/mod-super :glfw.core/mod-alt}
           (bit-or mod-super mod-alt mod-shift) #{:glfw.core/mod-super :glfw.core/mod-alt :glfw.core/mod-shift}
           (bit-or mod-super mod-alt mod-control) #{:glfw.core/mod-super :glfw.core/mod-alt :glfw.core/mod-control}
           (bit-or mod-super mod-alt mod-shift mod-control) #{:glfw.core/mod-super :glfw.core/mod-alt :glfw.core/mod-shift :glfw.core/mod-control}}
          mods
          mods)))))
(defn GLFWKeyCallback->fn [^GLFWKeyCallbackI this]
  (fn [window key scancode action mods]
    (.invoke this
      (long window)
      (case key
        {:glfw.core/key-unknown key-unknown
         :glfw.core/key-space key-space
         :glfw.core/key-apostrophe key-apostrophe
         :glfw.core/key-comma key-comma
         :glfw.core/key-minus key-minus
         :glfw.core/key-period key-period
         :glfw.core/key-slash key-slash
         :glfw.core/key-0 key-0
         :glfw.core/key-1 key-1
         :glfw.core/key-2 key-2
         :glfw.core/key-3 key-3
         :glfw.core/key-4 key-4
         :glfw.core/key-5 key-5
         :glfw.core/key-6 key-6
         :glfw.core/key-7 key-7
         :glfw.core/key-8 key-8
         :glfw.core/key-9 key-9
         :glfw.core/key-semicolon key-semicolon
         :glfw.core/key-equal key-equal
         :glfw.core/key-a key-a
         :glfw.core/key-b key-b
         :glfw.core/key-c key-c
         :glfw.core/key-d key-d
         :glfw.core/key-e key-e
         :glfw.core/key-f key-f
         :glfw.core/key-g key-g
         :glfw.core/key-h key-h
         :glfw.core/key-i key-i
         :glfw.core/key-j key-j
         :glfw.core/key-k key-k
         :glfw.core/key-l key-l
         :glfw.core/key-m key-m
         :glfw.core/key-n key-n
         :glfw.core/key-o key-o
         :glfw.core/key-p key-p
         :glfw.core/key-q key-q
         :glfw.core/key-r key-r
         :glfw.core/key-s key-s
         :glfw.core/key-t key-t
         :glfw.core/key-u key-u
         :glfw.core/key-v key-v
         :glfw.core/key-w key-w
         :glfw.core/key-x key-x
         :glfw.core/key-y key-y
         :glfw.core/key-z key-z
         :glfw.core/key-left-bracket key-left-bracket
         :glfw.core/key-backslash key-backslash
         :glfw.core/key-right-bracket key-right-bracket
         :glfw.core/key-grave-accent key-grave-accent
         :glfw.core/key-world-1 key-world-1
         :glfw.core/key-world-2 key-world-2
         :glfw.core/key-escape key-escape
         :glfw.core/key-enter key-enter
         :glfw.core/key-tab key-tab
         :glfw.core/key-backspace key-backspace
         :glfw.core/key-insert key-insert
         :glfw.core/key-delete key-delete
         :glfw.core/key-right key-right
         :glfw.core/key-left key-left
         :glfw.core/key-down key-down
         :glfw.core/key-up key-up
         :glfw.core/key-page-up key-page-up
         :glfw.core/key-page-down key-page-down
         :glfw.core/key-home key-home
         :glfw.core/key-end key-end
         :glfw.core/key-caps-lock key-caps-lock
         :glfw.core/key-scroll-lock key-scroll-lock
         :glfw.core/key-num-lock key-num-lock
         :glfw.core/key-print-screen key-print-screen
         :glfw.core/key-pause key-pause
         :glfw.core/key-f1 key-f1
         :glfw.core/key-f2 key-f2
         :glfw.core/key-f3 key-f3
         :glfw.core/key-f4 key-f4
         :glfw.core/key-f5 key-f5
         :glfw.core/key-f6 key-f6
         :glfw.core/key-f7 key-f7
         :glfw.core/key-f8 key-f8
         :glfw.core/key-f9 key-f9
         :glfw.core/key-f10 key-f10
         :glfw.core/key-f11 key-f11
         :glfw.core/key-f12 key-f12
         :glfw.core/key-f13 key-f13
         :glfw.core/key-f14 key-f14
         :glfw.core/key-f15 key-f15
         :glfw.core/key-f16 key-f16
         :glfw.core/key-f17 key-f17
         :glfw.core/key-f18 key-f18
         :glfw.core/key-f19 key-f19
         :glfw.core/key-f20 key-f20
         :glfw.core/key-f21 key-f21
         :glfw.core/key-f22 key-f22
         :glfw.core/key-f23 key-f23
         :glfw.core/key-f24 key-f24
         :glfw.core/key-f25 key-f25
         :glfw.core/key-kp-0 key-kp-0
         :glfw.core/key-kp-1 key-kp-1
         :glfw.core/key-kp-2 key-kp-2
         :glfw.core/key-kp-3 key-kp-3
         :glfw.core/key-kp-4 key-kp-4
         :glfw.core/key-kp-5 key-kp-5
         :glfw.core/key-kp-6 key-kp-6
         :glfw.core/key-kp-7 key-kp-7
         :glfw.core/key-kp-8 key-kp-8
         :glfw.core/key-kp-9 key-kp-9
         :glfw.core/key-kp-decimal key-kp-decimal
         :glfw.core/key-kp-divide key-kp-divide
         :glfw.core/key-kp-multiply key-kp-multiply
         :glfw.core/key-kp-subtract key-kp-subtract
         :glfw.core/key-kp-add key-kp-add
         :glfw.core/key-kp-enter key-kp-enter
         :glfw.core/key-kp-equal key-kp-equal
         :glfw.core/key-left-shift key-left-shift
         :glfw.core/key-left-control key-left-control
         :glfw.core/key-left-alt key-left-alt
         :glfw.core/key-left-super key-left-super
         :glfw.core/key-right-shift key-right-shift
         :glfw.core/key-right-control key-right-control
         :glfw.core/key-right-alt key-right-alt
         :glfw.core/key-right-super key-right-super
         :glfw.core/key-menu key-menu}
        key)
      (int scancode)
      (case action
        :glfw.core/press press
        :glfw.core/release release
        :glfw.core/repeat repeat
        action)
      (case mods
        #{:glfw.core/mod-shift} mod-shift
        #{:glfw.core/mod-control} mod-control
        #{:glfw.core/mod-control :glfw.core/mod-shift} (bit-or mod-shift mod-control)
        #{:glfw.core/mod-alt} mod-alt
        #{:glfw.core/mod-alt :glfw.core/mod-shift} (bit-or mod-alt mod-shift)
        #{:glfw.core/mod-alt :glfw.core/mod-control} (bit-or mod-alt mod-control)
        #{:glfw.core/mod-alt :glfw.core/mod-shift :glfw.core/mod-control} (bit-or mod-alt mod-shift mod-control)
        #{:glfw.core/mod-super} mod-super
        #{:glfw.core/mod-super :glfw.core/mod-shift} (bit-or mod-super mod-shift)
        #{:glfw.core/mod-super :glfw.core/mod-control} (bit-or mod-super mod-control)
        #{:glfw.core/mod-super :glfw.core/mod-shift :glfw.core/mod-control} (bit-or mod-super mod-shift mod-control)
        #{:glfw.core/mod-super :glfw.core/mod-alt} (bit-or mod-super mod-alt)
        #{:glfw.core/mod-super :glfw.core/mod-alt :glfw.core/mod-shift} (bit-or mod-super mod-alt mod-shift)
        #{:glfw.core/mod-super :glfw.core/mod-alt :glfw.core/mod-control} (bit-or mod-super mod-alt mod-control)
        #{:glfw.core/mod-super :glfw.core/mod-alt :glfw.core/mod-shift :glfw.core/mod-control} (bit-or mod-super mod-alt mod-shift mod-control)
        mods))))

(defn fn->GLFWMonitorCallback ^GLFWMonitorCallbackI [f]
  (reify GLFWMonitorCallbackI
    (invoke [_ monitor event]
      (f (long monitor)
        (get
          {connected :glfw.core/connected
           disconnected :glfw.core/disconnected}
          event
          event)))))
(defn GLFWMonitorCallback->fn [^GLFWMonitorCallbackI this]
  (fn [monitor event]
    (.invoke this (long monitor)
      (case event
        :glfw.core/connected connected
        :glfw.core/disconnected disconnected
        event))))

(defn fn->GLFWMouseButtonCallback ^GLFWMouseButtonCallbackI [f]
  (reify GLFWMouseButtonCallbackI
    (invoke [_ window button action mods]
      (f (long window)
        (get
          {mouse-button-1 :glfw.core/mouse-button-1
           mouse-button-2 :glfw.core/mouse-button-2
           mouse-button-3 :glfw.core/mouse-button-3
           mouse-button-4 :glfw.core/mouse-button-4
           mouse-button-5 :glfw.core/mouse-button-5
           mouse-button-6 :glfw.core/mouse-button-6
           mouse-button-7 :glfw.core/mouse-button-7
           mouse-button-8 :glfw.core/mouse-button-8
           mouse-button-left :glfw.core/mouse-button-left
           mouse-button-right :glfw.core/mouse-button-right
           mouse-button-middle :glfw.core/mouse-button-middle}
          button
          button)
        (get
          {press :glfw.core/press
           release :glfw.core/release}
          action
          action)
        (get
          {mod-shift #{:glfw.core/mod-shift}
           mod-control #{:glfw.core/mod-control}
           (bit-or mod-shift mod-control) #{:glfw.core/mod-control :glfw.core/mod-shift}
           mod-alt #{:glfw.core/mod-alt}
           (bit-or mod-alt mod-shift) #{:glfw.core/mod-alt :glfw.core/mod-shift}
           (bit-or mod-alt mod-control) #{:glfw.core/mod-alt :glfw.core/mod-control}
           (bit-or mod-alt mod-shift mod-control) #{:glfw.core/mod-alt :glfw.core/mod-shift :glfw.core/mod-control}
           mod-super #{:glfw.core/mod-super}
           (bit-or mod-super mod-shift) #{:glfw.core/mod-super :glfw.core/mod-shift}
           (bit-or mod-super mod-control) #{:glfw.core/mod-super :glfw.core/mod-control}
           (bit-or mod-super mod-shift mod-control) #{:glfw.core/mod-super :glfw.core/mod-shift :glfw.core/mod-control}
           (bit-or mod-super mod-alt) #{:glfw.core/mod-super :glfw.core/mod-alt}
           (bit-or mod-super mod-alt mod-shift) #{:glfw.core/mod-super :glfw.core/mod-alt :glfw.core/mod-shift}
           (bit-or mod-super mod-alt mod-control) #{:glfw.core/mod-super :glfw.core/mod-alt :glfw.core/mod-control}
           (bit-or mod-super mod-alt mod-shift mod-control) #{:glfw.core/mod-super :glfw.core/mod-alt :glfw.core/mod-shift :glfw.core/mod-control}}
          mods
          mods)))))
(defn GLFWMouseButtonCallback->fn [^GLFWMouseButtonCallbackI this]
  (fn [window button action mods]
    (.invoke this
      (long window)
      (case button
        :glfw.core/mouse-button-1 mouse-button-1
        :glfw.core/mouse-button-2 mouse-button-2
        :glfw.core/mouse-button-3 mouse-button-3
        :glfw.core/mouse-button-4 mouse-button-4
        :glfw.core/mouse-button-5 mouse-button-5
        :glfw.core/mouse-button-6 mouse-button-6
        :glfw.core/mouse-button-7 mouse-button-7
        :glfw.core/mouse-button-8 mouse-button-8
        :glfw.core/mouse-button-left mouse-button-left
        :glfw.core/mouse-button-right mouse-button-right
        :glfw.core/mouse-button-middle mouse-button-middle)
      (case action
        :glfw.core/press press
        :glfw.core/release release
        action)
      (case mods
        #{:glfw.core/mod-shift} mod-shift
        #{:glfw.core/mod-control} mod-control
        #{:glfw.core/mod-control :glfw.core/mod-shift} (bit-or mod-shift mod-control)
        #{:glfw.core/mod-alt} mod-alt
        #{:glfw.core/mod-alt :glfw.core/mod-shift} (bit-or mod-alt mod-shift)
        #{:glfw.core/mod-alt :glfw.core/mod-control} (bit-or mod-alt mod-control)
        #{:glfw.core/mod-alt :glfw.core/mod-shift :glfw.core/mod-control} (bit-or mod-alt mod-shift mod-control)
        #{:glfw.core/mod-super} mod-super
        #{:glfw.core/mod-super :glfw.core/mod-shift} (bit-or mod-super mod-shift)
        #{:glfw.core/mod-super :glfw.core/mod-control} (bit-or mod-super mod-control)
        #{:glfw.core/mod-super :glfw.core/mod-shift :glfw.core/mod-control} (bit-or mod-super mod-shift mod-control)
        #{:glfw.core/mod-super :glfw.core/mod-alt} (bit-or mod-super mod-alt)
        #{:glfw.core/mod-super :glfw.core/mod-alt :glfw.core/mod-shift} (bit-or mod-super mod-alt mod-shift)
        #{:glfw.core/mod-super :glfw.core/mod-alt :glfw.core/mod-control} (bit-or mod-super mod-alt mod-control)
        #{:glfw.core/mod-super :glfw.core/mod-alt :glfw.core/mod-shift :glfw.core/mod-control} (bit-or mod-super mod-alt mod-shift mod-control)
        mods))))

(defn fn->GLFWScrollCallback ^GLFWScrollCallbackI [f]
  (reify GLFWScrollCallbackI
    (invoke [_ window x y]
      (f (long window) (double x) (double y)))))
(defn GLFWScrollCallback->fn [^GLFWScrollCallbackI this]
  (fn [window x y]
    (.invoke this (long window) (double x) (double y))))

(defn fn->GLFWScrollCallback ^GLFWScrollCallbackI [f]
  (reify GLFWScrollCallbackI
    (invoke [_ window x y]
      (f (long window) (double x) (double y)))))
(defn GLFWScrollCallback->fn [^GLFWScrollCallbackI this]
  (fn [window x y]
    (.invoke this (long window) (double x) (double y))))

(defn fn->GLFWWindowCloseCallback ^GLFWWindowCloseCallbackI [f]
  (reify GLFWWindowCloseCallbackI
    (invoke [_ window]
      (f (long window)))))
(defn GLFWWindowCloseCallback->fn [^GLFWWindowCloseCallbackI this]
  (fn [window]
    (.invoke this (long window))))

(defn fn->GLFWWindowFocusCallback ^GLFWWindowFocusCallbackI [f]
  (reify GLFWWindowFocusCallbackI
    (invoke [_ window focused]
      (f (long window)
        (case focused
          :glfw.core/true true
          :glfw.core/false false
          focused)))))
(defn GLFWWindowFocusCallback->fn [^GLFWWindowFocusCallbackI this]
  (fn [window focused]
    (.invoke this
      (long window)
      (case focused
        true :glfw.core/true
        false :glfw.core/false
        focused))))

(defn fn->GLFWWindowIconifyCallback ^GLFWWindowIconifyCallbackI [f]
  (reify GLFWWindowIconifyCallbackI
    (invoke [_ window iconified]
      (f (long window)
        (case iconified
          :glfw.core/true true
          :glfw.core/false false
          iconified)))))
(defn GLFWWindowIconifyCallback->fn [^GLFWWindowIconifyCallbackI this]
  (fn [window iconified]
    (.invoke this
      (long window)
      (case focused
        true :glfw.core/true
        false :glfw.core/false
        focused))))

(defn fn->GLFWWindowMaximizeCallback ^GLFWWindowMaximizeCallbackI [f]
  (reify GLFWWindowMaximizeCallbackI
    (invoke [_ window maximized]
      (f (long window)
        (case maximized
          :glfw.core/true true
          :glfw.core/false false
          maximized)))))
(defn GLFWWindowMaximizeCallback->fn [^GLFWWindowMaximizeCallbackI this]
  (fn [window maximized]
    (.invoke this
      (long window)
      (case maximized
        true :glfw.core/true
        false :glfw.core/false
        maximized))))

(defn fn->GLFWWindowPosCallback ^GLFWWindowPosCallbackI [f]
  (reify GLFWWindowPosCallbackI
    (invoke [_ window x y]
      (f (long window) (int x) (int y)))))
(defn GLFWWindowPosCallback->fn [^GLFWWindowPosCallbackI this]
  (fn [window x y]
    (.invoke this (long window) (int x) (int y))))

(defn fn->GLFWWindowRefreshCallback ^GLFWWindowRefreshCallbackI [f]
  (reify GLFWWindowRefreshCallbackI
    (invoke [_ window]
      (f (long window)))))
(defn GLFWWindowRefreshCallback->fn [^GLFWWindowRefreshCallbackI this]
  (fn [window]
    (.invoke this (long window))))

(defn fn->GLFWWindowSizeCallback ^GLFWWindowSizeCallbackI [f]
  (reify GLFWWindowSizeCallbackI
    (invoke [_ window width height]
      (f (long window) (int width) (int height)))))
(defn GLFWWindowSizeCallback->fn [^GLFWWindowSizeCallbackI this]
  (fn [window width height]
    (.invoke this (long window) (int width) (int height))))

(defn fn->VkAllocationFunction ^VkAllocationFunctionI [f]
  (reify VkAllocationFunctionI
    (invoke [_ user-data size alignment allocation-scope]
      (f (long user-data) (long size) (long alignment) (int allocation-scope)))))

(defn fn->VkReallocationFunction ^VkReallocationFunctionI [f]
  (reify VkReallocationFunctionI
    (invoke [_ user-data original size alignment allocation-scope]
      (f (long user-data) (long original) (long size) (long alignment) (int allocation-scope)))))

(defn fn->VkFreeFunction ^VkFreeFunctionI [f]
  (reify VkFreeFunctionI
    (invoke [_ user-data memory]
      (f (long user-data) (long memory)))))

(defn fn->VkInternalAllocationNotification ^VkInternalAllocationNotificationI [f]
  (reify VkInternalAllocationNotificationI
    (invoke [_ user-data size allocation-type allocation-scope]
      (f (long user-data) (long size) (int allocation-type) (int allocation-scope)))))

(defn fn->VkInternalFreeNotification ^VkInternalFreeNotificationI [f]
  (reify VkInternalFreeNotificationI
    (invoke [_ user-data size allocation-type allocation-scope]
      (f (long user-data) (long size) (int allocation-type) (int allocation-scope)))))

(defmulti free class)
(defmethod free :default [this] nil)

(defn str->ByteBuffer
  ^java.nio.ByteBuffer
  [string]
  (MemoryUtil/memUTF8 string))
(defn ByteBuffer->str [this]
  (MemoryUtil/memUTF8 this (.limit this) 0))

(defn seq->ByteBuffer ^java.nio.ByteBuffer [s]
  (doto (MemoryUtil/memAlloc (count s))
    (.put (into-array Byte/TYPE s))
    (.flip)))
(defmethod free java.nio.ByteBuffer [this]
  (MemoryUtil/memFree this))
(defn ByteBuffer->seq [this]
  (into [] (map (fn [i] (.get this i))) (range (.limit this))))

(defn seq->FloatBuffer ^java.nio.FloatBuffer [s]
  (doto (MemoryUtil/memAllocFloat (count s))
    (.put (into-array Float/TYPE s))
    (.flip)))
(defmethod free java.nio.FloatBuffer [this]
  (MemoryUtil/memFree this))
(defn FloatBuffer->seq [this]
  (into [] (map (fn [i] (.get this i))) (range (.limit this))))

(defn seq->DoubleBuffer ^java.nio.DoubleBuffer [s]
  (doto (MemoryUtil/memAllocDouble (count s))
    (.put (into-array Double/TYPE s))
    (.flip)))
(defmethod free java.nio.DoubleBuffer [this]
  (MemoryUtil/memFree this))
(defn DoubleBuffer->seq [this]
  (into [] (map (fn [i] (.get this i))) (range (.limit this))))

(defn seq->ShortBuffer ^java.nio.ShortBuffer [s]
  (doto (MemoryUtil/memAllocShort (count s))
    (.put (into-array Short/TYPE s))
    (.flip)))
(defmethod free java.nio.ShortBuffer [this]
  (MemoryUtil/memFree this))
(defn ShortBuffer->seq [this]
  (into [] (map (fn [i] (.get this i))) (range (.limit this))))

(defn seq->IntBuffer ^java.nio.IntBuffer [s]
  (doto (MemoryUtil/memAllocInt (count s))
    (.put (into-array Integer/TYPE s))
    (.flip)))
(defmethod free java.nio.IntBuffer [this]
  (MemoryUtil/memFree this))
(defn IntBuffer->seq [this]
  (into [] (map (fn [i] (.get this i))) (range (.limit this))))

(defn seq->LongBuffer ^java.nio.LongBuffer [s]
  (doto (MemoryUtil/memAllocLong (count s))
    (.put (into-array Long/TYPE s))
    (.flip)))
(defmethod free java.nio.LongBuffer [this]
  (MemoryUtil/memFree this))
(defn LongBuffer->seq [this]
  (into [] (map (fn [i] (.get this i))) (range (.limit this))))

(defn seq->PointerBuffer ^PointerBuffer [s]
  (doto (MemoryUtil/memAllocPointer (count s))
    (.put (into-array Long/TYPE s))
    (.flip)))
(defmethod free PointerBuffer [this]
  (MemoryUtil/memFree this))
(defn PointerBuffer->seq [this]
  (into [] (map (fn [i] (.get this i))) (range (.limit this))))

(defn map->GLFWGammaRamp [{:keys [red green blue size]}]
  (.set (GLFWGammaRamp/malloc)
    (seq->ShortBuffer red)
    (seq->ShortBuffer green)
    (seq->ShortBuffer blue)
    (int size)))
(defmethod free GLFWGammaRamp [this]
  (free (.red this))
  (free (.green this))
  (free (.blue this))
  (.free this))
(defn GLFWGammaRamp->map [this]
  {:red (ShortBuffer->seq (.red this))
   :green (ShortBuffer->seq (.green this))
   :blue (ShortBuffer->seq (.blue this))
   :size (int (.size this))})
(defn seq->GLFWGammaRamp$Buffer [s]
  (let [buffer (GLFWGammaRamp/malloc (count s))]
    (doseq [{:keys [red green blue size]} s]
      (.set (.get buffer)
        (seq->ShortBuffer red)
        (seq->ShortBuffer green)
        (seq->ShortBuffer blue)
        (int size)))
    (.flip buffer)))
(defmethod free GLFWGammaRamp$Buffer [this]
  (dotimes [n (.limit this)]
    (let [this (.get this n)]
      (free (.red this))
      (free (.green this))
      (free (.blue this))))
  (.free this))
(defn GLFWGammaRamp$Buffer->seq [this]
  (into [] (map GLFWGammaRamp->map) (for [i (range (.limit this))] (.get this i))))

(defn map->GLFWImage [{:keys [width height pixels]}]
  (.set (GLFWImage/malloc)
    (int width)
    (int height)
    (seq->ByteBuffer pixels)))
(defmethod free GLFWImage [this]
  (free (.pixels this))
  (.free this))
(defn GLFWImage->map [this]
  {:width (int (.width this))
   :height (int (.height this))
   :pixels (ByteBuffer->seq (.pixels this))})
(defn seq->GLFWImage$Buffer [s]
  (let [buffer (GLFWImage/malloc (count s))]
    (doseq [{:keys [width height pixels]} s]
      (.set (.get buffer)
        (int width)
        (int height)
        (seq->ByteBuffer pixels)))
    (.flip buffer)))
(defmethod free GLFWImage$Buffer [this]
  (dotimes [n (.limit this)]
    (let [this (.get this n)]
      (free (.pixels this))))
  (.free this))
(defn GLFWImage$Buffer->seq [this]
  (into [] (map GLFWImage->map) (for [i (range (.limit this))] (.get this i))))

(defn GLFWVidMode->map [this]
  {:width (int (.width this))
   :height (int (.height this))
   :red-bits (int (.redBits this))
   :green-bits (int (.greenBits this))
   :blue-bits (int (.blueBits this))
   :refresh-rate (int (.refreshRate this))})
(defmethod free GLFWVidMode [this]
  (.free this))
(defn GLFWVidMode$Buffer->seq [this]
  (into [] (map GLFWVidMode->map) (for [i (range (.limit this))] (.get this i))))
(defmethod free GLFWVidMode$Buffer [this]
  (.free this))

(defn map->VkAllocationCallbacks
  ^VkAllocationCallbacks
  [{:keys [user-data allocation reallocation free internal-allocation internal-free]}]
  (.set (VkAllocationCallbacks/malloc)
    (long user-data)
    (fn->VkAllocationFunction allocation)
    (fn->VkReallocationFunction reallocation)
    (fn->VkFreeFunction free)
    (fn->VkInternalAllocationNotification internal-allocation)
    (fn->VkInternalFreeNotification internal-free)))
(defmethod free VkAllocationCallbacks [this]
  (.free this))

(defn cocoa []
  {:monitor GLFWNativeCocoa$Functions/GetCocoaMonitor
   :window GLFWNativeCocoa$Functions/GetCocoaWindow})

(defn get-cocoa-window ^long [window]
  (GLFWNativeCocoa/glfwGetCocoaWindow (long window)))

(defn get-cocoa-monitor [monitor]
  (GLFWNativeCocoa/glfwGetCocoaMonitor (long monitor)))

(defn egl []
  {:context GLFWNativeEGL$Functions/GetEGLContext
   :display GLFWNativeEGL$Functions/GetEGLDisplay
   :surface GLFWNativeEGL$Functions/GetEGLSurface})

(defn get-egl-context ^long [window]
  (GLFWNativeEGL/glfwGetEGLContext (long window)))

(defn get-egl-display ^long []
  (GLFWNativeEGL/glfwGetEGLDisplay))

(defn get-egl-surface ^long [window]
  (GLFWNativeEGL/glfwGetEGLSurface (long window)))

(defn glx []
  {:context GLFWNativeGLX$Functions/GetGLXContext
   :window GLFWNativeGLX$Functions/GetGLXWindow})

(defn get-glx-context ^long [window]
  (GLFWNativeGLX/glfwGetGLXContext (long window)))

(defn get-glx-window ^long [window]
  (GLFWNativeGLX/glfwGetGLXWindow (long window)))

(defn nsgl []
  {:context GLFWNativeNSGL$Functions/GetNSGLContext})

(defn get-nsgl-context ^long [window]
  (GLFWNativeNSGL/glfwGetNSGLContext (long window)))

(defn wgl []
  {:context GLFWNativeWGL$Functions/GetWGLContext})

(defn get-wgl-context ^long [window]
  (GLFWNativeWGL/glfwGetWGLContext (long window)))

(defn win32 []
  {:adapter GLFWNativeWin32$Functions/GetWin32Adapter
   :monitor GLFWNativeWin32$Functions/GetWin32Monitor
   :window GLFWNativeWin32$Functions/GetWin32Window})

(defn get-win32-adapter ^java.lang.String [monitor]
  (GLFWNativeWin32/glfwGetWin32Adapter (long monitor)))

(defn get-win32-monitor ^java.lang.String [monitor]
  (GLFWNativeWin32/glfwGetWin32Monitor (long monitor)))

(defn get-win32-window ^long [window]
  (GLFWNativeWin32/glfwGetWin32Window (long window)))

(defn x11 []
  {:adapter GLFWNativeX11$Functions/GetX11Adapter
   :display GLFWNativeX11$Functions/GetX11Display
   :monitor GLFWNativeX11$Functions/GetX11Monitor
   :window GLFWNativeX11$Functions/GetX11Window})

(defn get-x11-adapter ^long [monitor]
  (GLFWNativeX11/glfwGetX11Adapter (long monitor)))

(defn get-x11-display ^long []
  (GLFWNativeX11/glfwGetX11Display))

(defn get-x11-monitor ^long [monitor]
  (GLFWNativeX11/glfwGetX11Monitor (long monitor)))

(defn get-x11-window ^long [window]
  (GLFWNativeX11/glfwGetX11Window (long window)))

(defn create-window-surface [^VkInstance instance window allocator surface]
  (let [INSTANCE instance
        WINDOW (long window)
        ALLOCATOR (map->VkAllocationCallbacks allocator)
        SURFACE (seq->LongBuffer surface)
        RESULT (GLFWVulkan/glfwCreateWindowSurface INSTANCE WINDOW ALLOCATOR SURFACE)]
    (free ALLOCATOR)
    (free SURFACE)
    RESULT))

(defn get-instance-proc-address ^long [^VkInstance instance procname]
  (let [INSTANCE instance
        PROCNAME (str->ByteBuffer procname)
        RESULT (GLFWVulkan/glfwGetInstanceProcAddress INSTANCE PROCNAME)]
    (free PROCNAME)
    RESULT))

(defn get-physical-device-presentation-support [^VkInstance instance device queue-family]
  (let [INSTANCE instance
        DEVICE device
        QUEUE-FAMILY (int queue-family)]
    (GLFWVulkan/glfwGetPhysicalDevicePresentationSupport INSTANCE DEVICE QUEUE-FAMILY)))

(defn get-required-instance-extensions []
  (into [] (map (fn [i] (MemoryUtil/memASCII i))) (PointerBuffer->seq (GLFWVulkan/glfwGetRequiredInstanceExtensions))))

(defn vulkan-supported []
  (GLFWVulkan/glfwVulkanSupported))

(defn vulkan []
  {:create-window-surface GLFWVulkan$Functions/CreateWindowSurface
   :get-instance-proc-address GLFWVulkan$Functions/GetInstanceProcAddress
   :get-physical-device-presentation-support GLFWVulkan$Functions/GetPhysicalDevicePresentationSupport
   :get-required-instance-extensions GLFWVulkan$Functions/GetRequiredInstanceExtensions
   :vulkan-supported GLFWVulkan$Functions/VulkanSupported})
