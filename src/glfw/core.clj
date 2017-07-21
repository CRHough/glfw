(ns glfw.core
  (:refer-clojure :exclude [repeat])
  (:require [glfw.implementation :as impl])
  (:import org.lwjgl.glfw.GLFW
           [org.lwjgl.system
             MemoryUtil
             SharedLibrary]))

(defn interpret [val]
  (case val
    ::version-major impl/version-major
    ::version-minor impl/version-minor
    ::version-revision impl/version-revision
    ::true impl/TRUE
    ::false impl/FALSE
    ::release impl/release
    ::press impl/press
    ::repeat impl/repeat
    ::hat-centered impl/hat-centered
    ::hat-up impl/hat-up
    ::hat-right impl/hat-right
    ::hat-down impl/hat-down
    ::hat-left impl/hat-left
    ::hat-right-up impl/hat-right-up
    ::hat-right-down impl/hat-right-down
    ::hat-left-up impl/hat-left-up
    ::hat-left-down impl/hat-left-down
    ::key-unknown impl/key-unknown
    ::key-space impl/key-space
    ::key-apostrophe impl/key-apostrophe
    ::key-comma impl/key-comma
    ::key-minus impl/key-minus
    ::key-period impl/key-period
    ::key-slash impl/key-slash
    ::key-0 impl/key-0
    ::key-1 impl/key-1
    ::key-2 impl/key-2
    ::key-3 impl/key-3
    ::key-4 impl/key-4
    ::key-5 impl/key-5
    ::key-6 impl/key-6
    ::key-7 impl/key-7
    ::key-8 impl/key-8
    ::key-9 impl/key-9
    ::key-semicolon impl/key-semicolon
    ::key-equal impl/key-equal
    ::key-a impl/key-a
    ::key-b impl/key-b
    ::key-c impl/key-c
    ::key-d impl/key-d
    ::key-e impl/key-e
    ::key-f impl/key-f
    ::key-g impl/key-g
    ::key-h impl/key-h
    ::key-i impl/key-i
    ::key-j impl/key-j
    ::key-k impl/key-k
    ::key-l impl/key-l
    ::key-m impl/key-m
    ::key-n impl/key-n
    ::key-o impl/key-o
    ::key-p impl/key-p
    ::key-q impl/key-q
    ::key-r impl/key-r
    ::key-s impl/key-s
    ::key-t impl/key-t
    ::key-u impl/key-u
    ::key-v impl/key-v
    ::key-w impl/key-w
    ::key-x impl/key-x
    ::key-y impl/key-y
    ::key-z impl/key-z
    ::key-left-bracket impl/key-left-bracket
    ::key-backslash impl/key-backslash
    ::key-right-bracket impl/key-right-bracket
    ::key-grave-accent impl/key-grave-accent
    ::key-world-1 impl/key-world-1
    ::key-world-2 impl/key-world-2
    ::key-escape impl/key-escape
    ::key-enter impl/key-enter
    ::key-tab impl/key-tab
    ::key-backspace impl/key-backspace
    ::key-insert impl/key-insert
    ::key-delete impl/key-delete
    ::key-right impl/key-right
    ::key-left impl/key-left
    ::key-down impl/key-down
    ::key-up impl/key-up
    ::key-page-up impl/key-page-up
    ::key-page-down impl/key-page-down
    ::key-home impl/key-home
    ::key-end impl/key-end
    ::key-caps-lock impl/key-caps-lock
    ::key-scroll-lock impl/key-scroll-lock
    ::key-num-lock impl/key-num-lock
    ::key-print-screen impl/key-print-screen
    ::key-pause impl/key-pause
    ::key-f1 impl/key-f1
    ::key-f2 impl/key-f2
    ::key-f3 impl/key-f3
    ::key-f4 impl/key-f4
    ::key-f5 impl/key-f5
    ::key-f6 impl/key-f6
    ::key-f7 impl/key-f7
    ::key-f8 impl/key-f8
    ::key-f9 impl/key-f9
    ::key-f10 impl/key-f10
    ::key-f11 impl/key-f11
    ::key-f12 impl/key-f12
    ::key-f13 impl/key-f13
    ::key-f14 impl/key-f14
    ::key-f15 impl/key-f15
    ::key-f16 impl/key-f16
    ::key-f17 impl/key-f17
    ::key-f18 impl/key-f18
    ::key-f19 impl/key-f19
    ::key-f20 impl/key-f20
    ::key-f21 impl/key-f21
    ::key-f22 impl/key-f22
    ::key-f23 impl/key-f23
    ::key-f24 impl/key-f24
    ::key-f25 impl/key-f25
    ::key-kp-0 impl/key-kp-0
    ::key-kp-1 impl/key-kp-1
    ::key-kp-2 impl/key-kp-2
    ::key-kp-3 impl/key-kp-3
    ::key-kp-4 impl/key-kp-4
    ::key-kp-5 impl/key-kp-5
    ::key-kp-6 impl/key-kp-6
    ::key-kp-7 impl/key-kp-7
    ::key-kp-8 impl/key-kp-8
    ::key-kp-9 impl/key-kp-9
    ::key-kp-decimal impl/key-kp-decimal
    ::key-kp-divide impl/key-kp-divide
    ::key-kp-multiply impl/key-kp-multiply
    ::key-kp-subtract impl/key-kp-subtract
    ::key-kp-add impl/key-kp-add
    ::key-kp-enter impl/key-kp-enter
    ::key-kp-equal impl/key-kp-equal
    ::key-left-shift impl/key-left-shift
    ::key-left-control impl/key-left-control
    ::key-left-alt impl/key-left-alt
    ::key-left-super impl/key-left-super
    ::key-right-shift impl/key-right-shift
    ::key-right-control impl/key-right-control
    ::key-right-alt impl/key-right-alt
    ::key-right-super impl/key-right-super
    ::key-menu impl/key-menu
    ::mod-shift impl/mod-shift
    ::mod-control impl/mod-control
    ::mod-alt impl/mod-alt
    ::mod-super impl/mod-super
    ::mouse-button-1 impl/mouse-button-1
    ::mouse-button-2 impl/mouse-button-2
    ::mouse-button-3 impl/mouse-button-3
    ::mouse-button-4 impl/mouse-button-4
    ::mouse-button-5 impl/mouse-button-5
    ::mouse-button-6 impl/mouse-button-6
    ::mouse-button-7 impl/mouse-button-7
    ::mouse-button-8 impl/mouse-button-8
    ::mouse-button-left impl/mouse-button-left
    ::mouse-button-right impl/mouse-button-right
    ::mouse-button-middle impl/mouse-button-middle
    ::joystick-1 impl/joystick-1
    ::joystick-2 impl/joystick-2
    ::joystick-3 impl/joystick-3
    ::joystick-4 impl/joystick-4
    ::joystick-5 impl/joystick-5
    ::joystick-6 impl/joystick-6
    ::joystick-7 impl/joystick-7
    ::joystick-8 impl/joystick-8
    ::joystick-9 impl/joystick-9
    ::joystick-10 impl/joystick-10
    ::joystick-11 impl/joystick-11
    ::joystick-12 impl/joystick-12
    ::joystick-13 impl/joystick-13
    ::joystick-14 impl/joystick-14
    ::joystick-15 impl/joystick-15
    ::joystick-16 impl/joystick-16
    ::no-error impl/no-error
    ::not-initialized impl/not-initialized
    ::no-current-context impl/no-current-context
    ::invalid-enum impl/invalid-enum
    ::invalid-value impl/invalid-value
    ::out-of-memory impl/out-of-memory
    ::api-unavailable impl/api-unavailable
    ::version-unavailable impl/version-unavailable
    ::platform-error impl/platform-error
    ::format-unavailable impl/format-unavailable
    ::no-window-context impl/no-window-context
    ::focused impl/focused
    ::iconified impl/iconified
    ::resizable impl/resizable
    ::visible impl/visible
    ::decorated impl/decorated
    ::auto-iconify impl/auto-iconify
    ::floating impl/floating
    ::maximized impl/maximized
    ::center-cursor impl/center-cursor
    ::cursor impl/cursor
    ::sticky-keys impl/sticky-keys
    ::sticky-mouse-buttons impl/sticky-mouse-buttons
    ::cursor-normal impl/cursor-normal
    ::cursor-hidden impl/cursor-hidden
    ::cursor-disabled impl/cursor-disabled
    ::arrow-cursor impl/arrow-cursor
    ::ibeam-cursor impl/ibeam-cursor
    ::crosshair-cursor impl/crosshair-cursor
    ::hand-cursor impl/hand-cursor
    ::hresize-cursor impl/hresize-cursor
    ::vresize-cursor impl/vresize-cursor
    ::connected impl/connected
    ::disconnected impl/disconnected
    ::joystick-hat-buttons impl/joystick-hat-buttons
    ::cocoa-chdir-resources impl/cocoa-chdir-resources
    ::cocoa-menubar impl/cocoa-menubar
    ::dont-care impl/dont-care
    ::red-bits impl/red-bits
    ::green-bits impl/green-bits
    ::blue-bits impl/blue-bits
    ::alpha-bits impl/alpha-bits
    ::depth-bits impl/depth-bits
    ::stencil-bits impl/stencil-bits
    ::accum-red-bits impl/accum-red-bits
    ::accum-green-bits impl/accum-green-bits
    ::accum-blue-bits impl/accum-blue-bits
    ::accum-alpha-bits impl/accum-alpha-bits
    ::aux-buffers impl/aux-buffers
    ::stereo impl/stereo
    ::samples impl/samples
    ::srgb-capable impl/srgb-capable
    ::refresh-rate impl/refresh-rate
    ::doublebuffer impl/doublebuffer
    ::client-api impl/client-api
    ::context-version-major impl/context-version-major
    ::context-version-minor impl/context-version-minor
    ::context-revision impl/context-revision
    ::context-robustness impl/context-robustness
    ::opengl-forward-compat impl/opengl-forward-compat
    ::opengl-debug-context impl/opengl-debug-context
    ::opengl-profile impl/opengl-profile
    ::context-release-behavior impl/context-release-behavior
    ::context-no-error impl/context-no-error
    ::context-creation-api impl/context-creation-api
    ::cocoa-retina-framebuffer impl/cocoa-retina-framebuffer
    ::cocoa-frame-autosave impl/cocoa-frame-autosave
    ::cocoa-graphics-switching impl/cocoa-graphics-switching
    ::no-api impl/no-api
    ::opengl-api impl/opengl-api
    ::opengl-es-api impl/opengl-es-api
    ::no-robustness impl/no-robustness
    ::no-reset-notification impl/no-reset-notification
    ::lose-context-on-reset impl/lose-context-on-reset
    ::opengl-any-profile impl/opengl-any-profile
    ::opengl-core-profile impl/opengl-core-profile
    ::opengl-compat-profile impl/opengl-compat-profile
    ::any-release-behavior impl/any-release-behavior
    ::release-behavior-flush impl/release-behavior-flush
    ::release-behavior-none impl/release-behavior-none
    ::native-context-api impl/native-context-api
    ::egl-context-api impl/egl-context-api
    ::osmesa-context-api impl/osmesa-context-api
    val))

(defn get-library
  "Returns the glfw SharedLibrary."
  ^org.lwjgl.system.SharedLibrary
  []
  (GLFW/getLibrary))

(defn create-cursor
  "Creates a new custom cursor image that can be set for a window with [[set-cursor]].

  The cursor can be destroyed with [[destroy-cursor]]. Any remaining cursors are destroyed by terminate.

  The pixels are 32-bit, little-endian, non-premultiplied RGBA, i.e. eight bits per channel. They are arranged canonically as packed sequential rows, starting from the top-left corner.

  The cursor hotspot is specified in pixels, relative to the upper-left corner of the cursor image. Like all other coordinate systems in GLFW, the X-axis points to the right and the Y-axis points down.

  ## Note
  * This function must only be called from the main thread.
  * The specified image data is copied before this function returns.

  ## Parameters
  image - the desired cursor image
  [x y] - the desired x and y coordinates, in pixels, of the cursor hotspot

  ## Returns
  the handle of the created cursor, or nil if an error occurred"
  [image [x y]]
  (let [IMAGE (impl/map->GLFWImage image)
        X (int x)
        Y (int y)]
    (impl/null->nil (GLFW/glfwCreateCursor IMAGE X Y))))

(defn create-standard-cursor
  "Returns a cursor with a standard shape, that can be set for a window with [[set-cursor]].

  | Shapes |
  | ------ |
  | ::arrow-cursor |
  | ::ibeam-cursor |
  | ::crosshair-cursor |
  | ::hand-cursor |
  | ::hresize-cursor |
  | ::vresize-cursor |

  ## Note
  * This function must only be called from the main thread.
  * The specified image data is copied before this function returns.

  ## Parameters
  shape - one of the standard shapes.

  ## Returns
  a new cursor ready to use or nil if an error occured"
  [shape]
  (let [SHAPE (int (interpret shape))]
    (impl/null->nil (GLFW/glfwCreateStandardCursor SHAPE))))

(defn create-window
  "Creates a window and its associated OpenGL or OpenGL ES context.

  Most of the options controlling how the window and its context should be created are specified with window hints.

  Successful creation does not change which context is current. Before you can use the newly created context, you need to make it current.

  The created window, framebuffer and context may differ from what you requested, as not all parameters and hints are hard constraints. This includes the size of the window, especially for full screen windows. To query the actual attributes of the created window, framebuffer and context, use queries like [[get-window-attrib]] and [[get-window-size]] and [[get-framebuffer-size]].

  To create a full screen window, you need to specify the monitor the window will cover. If no monitor is specified, the window will be windowed mode. Unless you have a way for the user to choose a specific monitor, it is recommended that you pick the primary monitor.

  For full screen windows, the specified size becomes the resolution of the window's desired video mode. As long as a full screen window is not iconified, the supported video mode most closely matching the desired video mode is set for the specified monitor.

  Once you have created the window, you can switch it between windowed and full screen mode with set-window-monitor. If the window has an OpenGL or OpenGL ES context, it will be unaffected.

  By default, newly created windows use the placement recommended by the window system. To create the window at a specific position, make it initially invisible using the VISIBLE window hint, set its position and then show it.

  As long as at least one full screen window is not iconified, the screensaver is prohibited from starting.

  Window systems put limits on window sizes. Very large or very small window dimensions may be overridden by the window system on creation. Check the actual size after creation.

  The swap interval is not set during window creation and the initial value may vary depending on driver settings and defaults.

  ## Note
  * This function must only be called from the main thread.
  * Windows: Window creation will fail if the Microsoft GDI software OpenGL implementation is the only one available.
  * Windows: If the executable has an icon resource named glfw-icon, it will be set as the initial icon for the window. If no such icon is present, the idi-winlogo icon will be used instead. To set a different icon, see [[set-window-icon]].
  * Windows: The context to share resources with may not be current on any other thread.
  * The OS only supports forward-compatible core profile contexts for OpenGL versions 3.2 and later. Before creating an OpenGL context of version 3.2 or later you must set the opengl-forward-compat and opengl-profile hints accordingly. OpenGL 3.0 and 3.1 contexts are not supported at all on macOS.
  * macOS: The GLFW window has no icon, as it is not a document window, but the dock icon will be the same as the application bundle's icon. For more information on bundles, see the Bundle Programming Guide in the Mac Developer Library.
  * macOS: The first time a window is created the menu bar is created. If GLFW finds a `MainMenu.nib` it is loaded and assumed to contain a menu bar. Otherwise a minimal menu bar is created manually with common commands like Hide, Quit and About. The About entry opens a minimal about dialog with information from the application's bundle. Menu bar creation can be disabled entirely with the cocoa-menubar init hint.
  * macOS: On macOS 10.10 and later the window frame will not be rendered at full resolution on Retina displays unless the cocoa-retina-framebuffer hint is true and the ns-high-resolution-capable key is enabled in the application bundle's Info.plist. For more information, see High Resolution Guidelines for macOS in the Mac Developer Library.
  * When activating frame autosaving with COCOA-FRAME-AUTOSAVE, the specified window size may be overriden by a previously saved size and position.
  * X11: Some window managers will not respect the placement of initially hidden windows.
  * X11: Due to the asynchronous nature of X11, it may take a moment for a window to reach its requested state. This means you may not be able to query the final size, position or other attributes directly after window creation.

  ## Parameters
  1. [width height] - the desired width and height, in screen coordinates, of the window
  2. title - initial UTF-8 encoded window title
  3. monitor - the monitor to use for fullscreen mode, or nil for windowed mode
  4. share - the window whose context to share resources with, or nil to not share resources

  ## Returns
  the handle of the created window, or nil if an error occurred"
  [[width height] title monitor share]
  (let [WIDTH (int width)
        HEIGHT (int height)
        TITLE (impl/str->ByteBuffer title)
        MONITOR (long (impl/nil->null monitor))
        SHARE (long (impl/nil->null share))
        RESULT (GLFW/glfwCreateWindow WIDTH HEIGHT TITLE MONITOR SHARE)]
    (impl/free TITLE)
    (impl/null->nil RESULT)))

(defn default-window-hints
  "Resets all window hints to their default values.

  See [[window-hint]] for details.

  ## Note
  * This function must only be called from the main thread."
  []
  (GLFW/glfwDefaultWindowHints))

(defn destroy-cursor
  "Destroys a cursor previously created with [[create-cursor]].

  Any remaining cursors will be destroyed by terminate.

  ## Note
  * This function must only be called from the main thread.
  * This function must not be called from a callback.

  ## Parameters
  1. cursor - the cursor object to destroy"
  [cursor]
  (let [CURSOR (long cursor)]
    (GLFW/glfwDestroyCursor CURSOR)))

(defn destroy-window
  "Destroys the specified window and its context.

   On calling this function, no further callbacks will be called for that window.

   If the context of the specified window is current on the main thread, it is detached before being destroyed.

   ## Note
   * This function must only be called from the main thread.
   * This function must not be called from a callback.
   * The context of the specified window must not be current on any other thread when this function is called.

   ## Parameters
   1. window - the window to destroy"
  [window]
  (let [WINDOW (long window)]
    (GLFW/glfwDestroyWindow WINDOW)))

(defn extension-supported
  "Returns whether the specified API extension is supported by the current OpenGL or OpenGL ES context.

  It searches both for client API extension and context creation API extensions.

  A context must be current on the calling thread. Calling this function without a current context will cause a no-current-context error.

  As this functions retrieves and searches one or more extension strings each call, it is recommended that you cache its results if it is going to be used frequently. The extension strings will not change during the lifetime of a context, so there is no danger in doing this.

  This function does not apply to Vulkan. If you are using Vulkan, see [[get-required-instance-extensions]], vkEnumerateInstanceExtensionProperties and vkEnumerateDeviceExtensionProperties instead.

  ## Note
  * This function may be called from any thread.

  ## Parameters
  extension - the ASCII encoded name of the extension

  ## Returns
  true if the extension is available, or false otherwise"
  [extension]
  (let [EXTENSION (impl/seq->ByteBuffer extension)]
    (GLFW/glfwExtensionSupported EXTENSION)))

(defn focus-window
  "Brings the specified window to front and sets input focus.

  The window should already be visible and not iconified.

  By default, both windowed and full screen mode windows are focused when initially created. Set the focused hint to disable this behavior.

  Do not use this function to steal focus from other applications unless you are certain that is what the user wants. Focus stealing can be extremely disruptive.

  For a less disruptive way of getting the user's attention, see [[request-window-attention]].

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window to give input focus"
  [window]
  (let [WINDOW (long window)]
    (GLFW/glfwFocusWindow WINDOW)))

(defn get-clipboard-string
  "Returns the contents of the system clipboard, if it contains or is convertible to a UTF-8 encoded string.

  If the clipboard is empty or if its contents cannot be converted, nil is returned and a format-unavailable error is generated.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window that will request the clipboard contents

  ## Returns
  the contents of the clipboard as a UTF-8 encoded string, or nil if an error occurred"
  ^java.lang.String
  [window]
  (let [WINDOW (long window)]
    (impl/null->nil (GLFW/glfwGetClipboardString WINDOW))))

(defn get-current-context
  "Returns the window whose OpenGL or OpenGL ES context is current on the calling thread.

  Returns the window whose OpenGL or OpenGL ES context is current on the calling thread.

  ## Note
  * This function may be called from any thread.

  ## Returns
  the window whose context is current, or nil if no window's context is current"
  []
  (impl/null->nil (GLFW/glfwGetCurrentContext)))

(defn get-cursor-pos
  "Returns the [x y] position of the cursor, in screen coordinates, relative to the upper-left corner of the client area of the specified window.

  If the cursor is disabled (with cursor-disabled) then the cursor position is unbounded and limited only by the minimum and maximum values of a double.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the desired window

  ## Returns
  a vector containing the x and y values"
  [window]
  (let [WINDOW (long window)
        XPOS (MemoryUtil/memAllocDouble 1)
        YPOS (MemoryUtil/memAllocDouble 1)]
    (GLFW/glfwGetCursorPos WINDOW XPOS YPOS)
    (let [Value [(.get XPOS 0) (.get YPOS 0)]]
      (MemoryUtil/memFree XPOS)
      (MemoryUtil/memFree YPOS)
      Value)))

(defn get-error []
  "Returns and clears the last error for the calling thread.

  This function returns and clears the error code of the last error that occurred on the calling thread. If no error has occurred since the last call, it returns no-error.

  ## Note
  * This function may be called before init.
  * This function may be called from any thread.

  ## Returns
  the last error code for the calling thread, or no-error"
  (GLFW/glfwGetError))

(defn get-framebuffer-size
  "Retrieves the [w h] size, in pixels, of the framebuffer of the specified window.

  If you wish to retrieve the size of the window in screen coordinates, see [[get-window-size]].

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window whose framebuffer to query

  ## Returns
  a vector containing the width and height values"
  [window]
  (let [WINDOW (long window)
        WIDTH (MemoryUtil/memAllocInt 1)
        HEIGHT (MemoryUtil/memAllocInt 1)]
    (GLFW/glfwGetFramebufferSize WINDOW WIDTH HEIGHT)
    (let [Value [(.get WIDTH 0) (.get HEIGHT 0)]]
      (MemoryUtil/memFree WIDTH)
      (MemoryUtil/memFree HEIGHT)
      Value)))

(defn get-gamma-ramp
  "Returns the current gamma ramp of the specified monitor.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. monitor - the monitor to query

  ## Returns
  the current gamma ramp, or nil if an error occurred"
  [monitor]
  (let [MONITOR (long monitor)
        GAMMARAMP (GLFW/glfwGetGammaRamp monitor)
        RESULT (impl/GLFWGammaRamp->map GAMMARAMP)]
    (impl/free GAMMARAMP)
    (impl/nil->null RESULT)))

(defn get-input-mode
  "Returns the value of an input option for the specified window.

  | Modes |
  | ------ |
  | cursor |
  | sticky-keys |
  | sticky-mouse-buttons |

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window to query
  2. mode - the input mode whose value to return.

  ## Returns
  the input mode value"
  [window mode]
  (let [WINDOW (long window)
        MODE (int (interpret mode))]
    (GLFW/glfwGetInputMode WINDOW MODE)))

(defn get-joystick-axes
  "Returns the values of all axes of the specified joystick.

  Each element in the array is a value between -1.0 and 1.0.

  Querying a joystick ID with no device present is not an error, but will cause this function to return nil. Call [[joystick-present]] to check device presence.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. jid - the joystick to query

  ## Returns
  an array of axis values, or nil if the joystick is not present"
  [jid]
  (let [JID (int jid)
        BUFFER (GLFW/glfwGetJoystickAxes JID)
        RESULT (impl/FloatBuffer->seq BUFFER)]
    (impl/free BUFFER)
    (impl/nil->null RESULT)))

(defn get-joystick-buttons
  "Returns the state of all buttons of the specified joystick.

  Each element in the array is either press or release.

  Querying a joystick ID with no device present is not an error, but will cause this function to return nil. Call [[joystick-present]] to check device presence.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. jid - the joystick to query

  ## Returns
  an array of button states, or NULL if the joystick is not present"
  [jid]
  (let [JID (int jid)
        BUFFER (GLFW/glfwGetJoystickButtons JID)
        RESULT (impl/ByteBuffer->seq BUFFER)]
    (impl/free BUFFER)
    (impl/nil->null RESULT)))

(defn get-joystick-hats
  "Returns the state of all hats of the specified joystick.

  This function returns a set of hats.

  | Return Values |
  | ------ |
  | #{::hat-centered} |
  | #{::hat-up} |
  | #{::hat-right} |
  | #{::hat-down} |
  | #{::hat-left} |
  | #{::hat-up ::hat-right ::hat-right-up} |
  | #{::hat-down ::hat-right ::hat-right-down} |
  | #{::hat-left ::hat-up ::hat-left-up} |
  | #{::hat-left ::hat-down ::hat-left-down} |

  Querying a joystick ID with no device present is not an error, but will cause this function to return nil. Call [[joystick-present]] to check device presence.

  ## Note
  * Linux: Joystick hats are currently unimplemented.
  * This function must only be called from the main thread.

  ## Parameters
  1. jid - the joystick to query

  ## Returns
  an set of hat states, or nil if the joystick is not present or an error occurred"
  [jid]
  (let [JID (int jid)
        BUFFER (GLFW/glfwGetJoystickHats JID)
        RESULT (impl/ByteBuffer->seq (impl/null->nil BUFFER))]
    (impl/free BUFFER)
    (map
      {impl/hat-centered #{::hat-centered}
       impl/hat-up #{::hat-up}
       impl/hat-right #{::hat-right}
       impl/hat-down #{::hat-down}
       impl/hat-left #{::hat-left}
       (bit-or impl/hat-up impl/hat-right) #{::hat-up ::hat-right ::hat-right-up}
       (bit-or impl/hat-down impl/hat-right) #{::hat-down ::hat-right ::hat-right-down}
       (bit-or impl/hat-left impl/hat-up) #{::hat-left ::hat-up ::hat-left-up}
       (bit-or impl/hat-left impl/hat-down) #{::hat-left ::hat-down ::hat-left-down}}
      RESULT)))

(defn get-joystick-name
  "Returns the name, encoded as UTF-8, of the specified joystick.

  Querying a joystick ID with no device present is not an error, but will cause this function to return nil. Call [[joystick-present]] to check device presence.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  jid - the joystick to query

  ## Returns
  the UTF-8 encoded name of the joystick, or nil if the joystick is not present"
  ^java.lang.String
  [jid]
  (let [JID (int jid)]
    (impl/null->nil (GLFW/glfwGetJoystickName JID))))

(defn get-key
  "Returns the last state reported for the specified key to the specified window.

  The returned state is one of press or release. The higher-level action repeat is only reported to the key callback.

  If the sticky-keys input mode is enabled, this function returns press the first time you call it for a key that was pressed, even if that key has already been released.

  The key functions deal with physical keys, with key tokens named after their use on the standard US keyboard layout. If you want to input text, use the Unicode character callback instead.

  The modifier key bit masks are not key tokens and cannot be used with this function.

  Do not use this function to implement text input.

  ## Note
  * This function must only be called from the main thread.
  * key-unkown is not a valid key for this function.

  ## Parameters
  1. window - the desired window
  2. key - the desired keyboard key

  ## Returns
  one of press or release"
  [window key]
  (let [WINDOW (long window)
        KEY (int (interpret key))]
    (get
      {impl/press ::press
       impl/release ::release}
      (GLFW/glfwGetKey WINDOW KEY))))

(defn get-key-name
  "Returns the layout-specific name of the specified printable key.

  This function returns the name of the specified printable key, encoded as UTF-8. This is typically the character that key would produce without any modifier keys, intended for displaying key bindings to the user. For dead keys, it is typically the diacritic it would add to a character.

  Do not use this function for text input. You will break text input for many languages even if it happens to work for yours.

  If the key is key-unkown, the scancode is used to identify the key, otherwise the scancode is ignored. If you specify a non-printable key, or key-unkown and a scancode that maps to a non-printable key, this function returns nil but does not emit an error.

  This behavior allows you to always pass in the arguments in the key callback without modification.

  | Printable keys |
  | ------ |
  | ::key-apostrophe |
  | ::key-comma |
  | ::key-minus |
  | ::key-period |
  | ::key-slash |
  | ::key-semicolon |
  | ::key-equal |
  | ::key-left-bracket |
  | ::key-right-bracket |
  | ::key-backslash |
  | ::key-world-1 |
  | ::key-world-2 |
  | ::key-0 to key-9 |
  | ::key-a to key-z |
  | ::key-kp-0 to key-kp-9 |
  | ::key-kp-decimal |
  | ::key-kp-divide |
  | ::key-kp-multiply |
  | ::key-kp-subtract |
  | ::key-kp-add |
  | ::key-kp-equal |

  Names for printable keys depend on keyboard layout, while names for non-printable keys are the same across layouts but depend on the application language and should be localized along with other user interface text.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. key - the key to query, or key-unkown
  2. scancode - the scancode of the key to query

  ## Returns
  the UTF-8 encoded, layout-specific name of the key, or nil"
  ^java.lang.String
  [key scancode]
  (let [KEY (int (interpret key))
        SCANCODE (int scancode)]
    (impl/null->nil (GLFW/glfwGetKeyName KEY SCANCODE))))

(defn get-key-scancode
  "Returns the platform dependent scancode of the specified key.

  This function returns the platform dependent scancode of the specified key. This is intended for platform specific default keybindings.

  If the key is key-unkown or does not exist on the keyboard this method will return nil.

  ## Note
  * This function may be called from any thread.

  ## Parameters
  1. key - the key to query, or key-unkown

  ## Returns
  the platform dependent scancode for the key, or nil if an errror occurred"
  [key]
  (let [KEY (int (interpret key))
        RESULT (GLFW/glfwGetKeyScancode KEY)]
    (if (= -1 RESULT) nil RESULT)))

(defn get-monitor-name
  "Returns a human-readable name, encoded as UTF-8, of the specified monitor.

  The name typically reflects the make and model of the monitor and is not guaranteed to be unique among the connected monitors.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. monitor - the monitor to query

  ## Returns
  the UTF-8 encoded name of the monitor, or nil if an error occured"
  ^java.lang.String
  [monitor]
  (let [MONITOR (long monitor)]
    (impl/null->nil (GLFW/glfwGetMonitorName monitor))))

(defn get-monitor-physical-size
  "Returns the [w h] size, in millimetres, of the display area of the specified monitor.

  Some systems do not provide accurate monitor size information, either because the monitor EDID data is incorrect or because the driver does not report it accurately.

  ## Note
  * This function must only be called from the main thread.
  * Windows: The OS calculates the returned physical size from the current resolution and system DPI instead of querying the monitor EDID data.

  ## Parameters
  * monitor - the monitor to query

  ## Returns
  a vector containing the width and height values"
  [monitor]
  (let [MONITOR (long monitor)
        WIDTH (MemoryUtil/memAllocInt 1)
        HEIGHT (MemoryUtil/memAllocInt 1)]
    (GLFW/glfwGetMonitorPhysicalSize MONITOR WIDTH HEIGHT)
    (let [Value [(.get WIDTH 0) (.get HEIGHT 0)]]
      (MemoryUtil/memFree WIDTH)
      (MemoryUtil/memFree HEIGHT)
      Value)))

(defn get-monitor-pos
  "Returns the [x y] position, in screen coordinates, of the upper-left corner of the specified monitor.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. monitor - the monitor to query

  ## Returns
  a vector containing the x and y values"
  [monitor]
  (let [MONITOR (long monitor)
        XPOS (MemoryUtil/memAllocInt 1)
        YPOS (MemoryUtil/memAllocInt 1)]
    (GLFW/glfwGetMonitorPos MONITOR XPOS YPOS)
    (let [Value [(.get XPOS 0) (.get YPOS 0)]]
      (MemoryUtil/memFree XPOS)
      (MemoryUtil/memFree YPOS)
      Value)))

(defn get-monitors
  "Returns an array of handles for all currently connected monitors.

  The primary monitor is always first in the returned array. If no monitors were found, this function returns nil.

  ## Note
  * This function must only be called from the main thread.

  ## Returns
  an array of monitor handlers, or NULL if no monitors were found or if an error occured"
  []
  (impl/PointerBuffer->seq (impl/null->nil (GLFW/glfwGetMonitors))))

(defn get-mouse-button
  "Returns the last state reported for the specified mouse button to the specified window.

  The returned state is one of press or release. The higher-level action repeat is only reported to the mouse button callback.

  If the sticky-mouse-buttons input mode is enabled, this function returns PRESS the first time you call it for a mouse button that has been pressed, even if that mouse button has already been released.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the desired window
  2. button - the desired mouse button

  ## Returns
  one of press or release"
  [window button]
  (let [WINDOW (long window)
        BUTTON (int (interpret button))]
    (get
      {impl/press ::press
       impl/release ::release}
      (GLFW/glfwGetMouseButton WINDOW BUTTON))))

(defn get-primary-monitor
  "Returns the primary monitor.

  This is usually the monitor where elements like the task bar or global menu bar are located.

  ## Note
  * This function must only be called from the main thread.
  * The primary monitor is always first in the array returned by GetMonitors.

  ## Returns
  the primary monitor, or nil if no monitors were found or if an error occured"
  []
  (impl/null->nil (GLFW/glfwGetPrimaryMonitor)))

(defn get-proc-address
  "Returns the address of the specified OpenGL or OpenGL ES core or extension function, if it is supported by the current context.

  A context must be current on the calling thread. Calling this function without a current context will cause a no-current-context error.

  This function does not apply to Vulkan. If you are rendering with Vulkan, get-instance-proc-address, vkGetInstanceProcAddr and vkGetDeviceProcAddr instead.

  ## Note
  * The address of a given function is not guaranteed to be the same between contexts.
  * This function may return a non-nil address despite the associated version or extension not being available. Always check the context version or extension string first.
  * The returned function pointer is valid until the context is destroyed or the library is terminated.
  * This function may be called from any thread.

  ## Parameters
  procname - the ASCII encoded name of the function

  ## Returns
  the address of the function, or nil if an error occured"
  [procname]
  (let [PROCNAME (impl/str->ByteBuffer procname)
        RESULT (GLFW/glfwGetProcAddress PROCNAME)]
    (impl/free PROCNAME)
    (impl/null->nil RESULT)))

(defn get-time
  "Returns the value of the GLFW timer.

  Unless the timer has been set using SetTime, the timer measures time elapsed since GLFW was initialized.

  The resolution of the timer is system dependent, but is usually on the order of a few micro- or nanoseconds. It uses the highest-resolution monotonic time source on each supported platform.

  ## Note
  * This function may be called from any thread.
  * Reading and writing of the internal timer offset is not atomic, so it needs to be externally synchronized with calls to set-time.

  ## Returns
  the current value, in seconds, or nil if an error occurred"
  []
  (impl/null->nil (GLFW/glfwGetTime)))

(defn get-timer-frequency
  "Returns the frequency, in Hz, of the raw timer.

  This function may be called from any thread.

  ## Returns
  the frequency of the timer, in Hz, or nil if an error occurred"
  []
  (impl/null->nil (GLFW/glfwGetTimerFrequency)))

(defn get-timer-value
  "Returns the current value of the raw timer.

  This function returns the current value of the raw timer, measured in 1 / frequency seconds. To get the frequency, call [[get-timer-frequency]].

  ## Note
  * This function may be called from any thread.

  ## Returns
  the value of the timer, or nil if an error occurred"
  []
  (impl/null->nil (GLFW/glfwGetTimerValue)))

(defn get-version
  "Retrieves the {:keys [major minor revision]} numbers of the GLFW library.

  It is intended for when you are using GLFW as a shared library and want to ensure that you are using the minimum required version.

  ## Note
  * This function always succeeds.
  * This function may be called before init.
  * This function may be called from any thread.

  ## Returns
  a map containing major, minor and revision keys with the associated values"
  []
  (let [MAJOR (MemoryUtil/memAllocInt 1)
        MINOR (MemoryUtil/memAllocInt 1)
        REV (MemoryUtil/memAllocInt 1)]
    (GLFW/glfwGetVersion MAJOR MINOR REV)
    (let [Value {:major (.get MAJOR 0)
                 :minor (.get MINOR 0)
                 :revision (.get REV 0)}]
      (MemoryUtil/memFree MAJOR)
      (MemoryUtil/memFree MINOR)
      (MemoryUtil/memFree REV)
      Value)))

(defn get-version-string
  "Returns the compile-time generated version string of the GLFW library binary.

  It describes the version, platform, compiler and any platform-specific compile-time options. It should not be confused with the OpenGL or OpenGL ES version string, queried with glGetString.

  Do not use the version string to parse the GLFW library version. The get-version function already provides the version of the library binary in numerical format.

  ## Note
  * This function always succeeds.
  * This function may be called before init.
  * This function may be called from any thread.
  * The returned string is static and compile-time generated.

  ## Returns
  The ASCII encoded GLFW version string."
  ^java.lang.String
  []
  (GLFW/glfwGetVersionString))

(defn get-video-mode
  "Returns the current video mode of the specified monitor.

  If you have created a full screen window for that monitor, the return value will depend on whether that window is iconified.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. monitor - the monitor to query

  ## Returns
  the current mode of the monitor, or nil if an error occurred"
  [monitor]
  (let [MONITOR (long monitor)]
    (impl/GLFWVidMode->map (impl/null->nil (GLFW/glfwGetVideoMode MONITOR)))))

(defn get-video-modes
  "Returns an array of all video modes supported by the specified monitor.

  The returned array is sorted in ascending order, first by color bit depth (the sum of all channel depths) and then by resolution area (the product of width and height).

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. monitor - the monitor to query

  ## Returns
  an array of video modes, or nil if an error occured"
  [monitor]
  (let [MONITOR (long monitor)]
    (impl/GLFWVidMode$Buffer->seq (impl/null->nil (GLFW/glfwGetVideoModes MONITOR)))))

(defn get-window-attrib
  "Returns the value of an attribute of the specified window or its OpenGL or OpenGL ES context.

  | Attributes |
  | ------ |
  | ::focused |
  | ::iconified |
  | ::resizable |
  | ::visible |
  | ::decorated |
  | ::floating |
  | ::maximized |
  | ::center-cursor |
  | ::client-api |
  | ::context-version-major |
  | ::context-version-minor |
  | ::context-revision |
  | ::context-robustness |
  | ::opengl-forward-compat |
  | ::opengl-debug-context |
  | ::opengl-profile |
  | ::context-release-behavior |
  | ::context-no-error |
  | ::context-creation-api |

  ## Note
  * This function must only be called from the main thread.
  * Framebuffer related hints are not window attributes.

  ## Parameters
  1. window - the window to query
  2. attrib - the window attribute whose value to return."
  [window attrib]
  (let [WINDOW (long window)
        ATTRIB (int (interpret attrib))]
    (GLFW/glfwGetWindowAttrib WINDOW ATTRIB)))

(defn get-window-frame-size
  "Retrieves the {:keys [left top right bottom]} border sizes, in screen coordinates, of the specified window.

  This size includes the title bar, if the window has one. The size of the frame may vary depending on the window-related hints used to create it.

  Because this function retrieves the size of each window frame edge and not the offset along a particular coordinate axis, the retrieved values will always be zero or positive.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window whose frame size to query

  ## Returns
  a map containing left, top, right and bottom keys with the associated values"
  [window]
  (let [WINDOW (long window)
        LEFT (MemoryUtil/memAllocInt 1)
        TOP (MemoryUtil/memAllocInt 1)
        RIGHT (MemoryUtil/memAllocInt 1)
        BOTTOM (MemoryUtil/memAllocInt 1)]
    (GLFW/glfwGetWindowFrameSize WINDOW LEFT TOP RIGHT BOTTOM)
    (let [Value {:left (.get LEFT 0)
                 :top (.get TOP 0)
                 :right (.get RIGHT 0)
                 :bottom (.get BOTTOM 0)}]
      (MemoryUtil/memFree LEFT)
      (MemoryUtil/memFree TOP)
      (MemoryUtil/memFree RIGHT)
      (MemoryUtil/memFree BOTTOM)
      Value)))

(defn get-window-monitor
  "Returns the handle of the monitor that the specified window is in full screen on.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window to query

  ## Returns
  the monitor, or nil if the window is in windowed mode or an error occurred"
  [window]
  (let [WINDOW (long window)]
    (impl/null->nil (GLFW/glfwGetWindowMonitor WINDOW))))

(defn get-window-pos
  "Retrieves the [x y] position, in screen coordinates, of the upper-left corner of the client area of the specified window.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window to query"
  [window]
  (let [WINDOW (long window)
        XPOS (MemoryUtil/memAllocInt 1)
        YPOS (MemoryUtil/memAllocInt 1)]
    (GLFW/glfwGetWindowPos WINDOW XPOS YPOS)
    (let [Value [(.get XPOS 0) (.get YPOS 0)]]
      (MemoryUtil/memFree XPOS)
      (MemoryUtil/memFree YPOS)
      Value)))

(defn get-window-size
  "Retrieves the [w h] size, in screen coordinates, of the client area of the specified window.

  If you wish to retrieve the size of the framebuffer of the window in pixels, see [[get-framebuffer-size]].

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  window - the window whose size to retrieve"
  [window]
  (let [WINDOW (long window)
        WIDTH (MemoryUtil/memAllocInt 1)
        HEIGHT (MemoryUtil/memAllocInt 1)]
    (GLFW/glfwGetWindowPos WINDOW WIDTH HEIGHT)
    (let [Value [(.get WIDTH 0) (.get HEIGHT 0)]]
      (MemoryUtil/memFree WIDTH)
      (MemoryUtil/memFree HEIGHT)
      Value)))

(defn get-window-user-pointer
  "Returns the current value of the user-defined pointer of the specified window.

  The initial value is 0.

  ## Note
  * This function may be called from any thread. Access is not synchronized.

  ## Parameters
  1. window - the window whose pointer to return"
  ^long
  [window]
  (let [WINDOW (long window)]
    (GLFW/glfwGetWindowUserPointer WINDOW)))

(defn hide-window
  "Hides the specified window, if it was previously visible.

  If the window is already hidden or is in full screen mode, this function does nothing.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window to hide"
  [window]
  (let [WINDOW (long window)]
    (GLFW/glfwHideWindow WINDOW)))

(defn iconify-window
  "Iconifies (minimizes) the specified window if it was previously restored.

  If the window is already iconified, this function does nothing.

  If the specified window is a full screen window, the original monitor resolution is restored until the window is restored.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window to iconify"
  [window]
  (let [WINDOW (long window)]
    (GLFW/glfwIconifyWindow WINDOW)))

(defn init
  "Initializes the GLFW library.

  Before most GLFW functions can be used, GLFW must be initialized, and before an application terminates GLFW should be terminated in order to free any resources allocated during or after initialization.
  If this function fails, it calls terminate before returning. If it succeeds, you should call [[terminate]] before the application exits.

  Additional calls to this function after successful initialization but before termination will return true immediately.

  ## Note
  * This function must only be called from the main thread.
  * This function will change the current directory of the application to the Contents/Resources subdirectory of the application's bundle, if present. This can be disabled with the cocoa-chdir-resources init hint.

  ## Returns
  true if successful, or false if an error occured"
  []
  (impl/int->boolean (GLFW/glfwInit)))

(defn init-hint
  "Sets the specified init hint to the desired value.
  This function sets hints for the next initialization of GLFW.

  The values you set are not affected by initialization or termination, but they are only read during initialization. Once GLFW has been initialized, setting new hint values will not affect behavior until the next time the library is terminated and initialized.

  Some hints are platform specific. These are always valid to set on any platform but they will only affect their specific platform. Other platforms will simply ignore them. Setting these hints requires no platform specific headers or calls.

  ## Note
  * This function may be called before init.
  * This function must only be called from the main thread.

  ## Parameters
  1. hint - the init hint to set. One of: ::joystick-hat-buttons ::cocoa-chdir-resources ::cocoa-menubar
  2. value - the new value of the init hint"
  [hint value]
  (let [HINT (int (interpret hint))
        VALUE (int (interpret value))]
    (GLFW/glfwInitHint HINT VALUE)))

(defn joystick-present
  "Returns whether the specified joystick is present.

  ## Note
  * This function must only be called from the main thread.

  *Parameters
  1. jid - joystick to query

  ## Returns
  true if the joystick is present, or false otherwise"
  [jid]
  (let [JID (int jid)]
    (GLFW/glfwJoystickPresent JID)))

(defn make-context-current
  "Makes the OpenGL or OpenGL ES context of the specified window current on the calling thread.

  A context can only be made current on a single thread at a time and each thread can have only a single current context at a time.

  By default, making a context non-current implicitly forces a pipeline flush. On machines that support GL-KHR-context-flush-control, you can control whether a context performs this flush by setting the context-release-behavior window hint.

  The specified window must have an OpenGL or OpenGL ES context. Specifying a window without a context will generate a no-window-context error.

  ## Note
  * This function may be called from any thread.

  * Parameters
  1. window - the window whose context to make current, or nil to detach the current context"
  [window]
  (let [WINDOW (long (impl/nil->null window))]
    (GLFW/glfwMakeContextCurrent WINDOW)))

(defn maximize-window
  "Maximizes the specified window if it was previously not maximized.

  If the window is already maximized, this function does nothing.

  If the specified window is a full screen window, this function does nothing.

  ## Note
  * This function may only be called from the main thread.

  ## Parameters
  1. window - the window to maximize"
  [window]
  (let [WINDOW (long window)]
    (GLFW/glfwMaximizeWindow WINDOW)))

(defn poll-events
  "Processes all pending events.

  This function processes only those events that are already in the event queue and then returns immediately. Processing events will cause the window and input callbacks associated with those events to be called.

  On some platforms, a window move, resize or menu operation will cause event processing to block. This is due to how event processing is designed on those platforms. You can use the window refresh callback to redraw the contents of your window when necessary during such operations.

  On some platforms, certain events are sent directly to the application without going through the event queue, causing callbacks to be called outside of a call to one of the event processing functions.

  Event processing is not required for joystick input to work.

  ## Note
  * This function must only be called from the main thread.
  * This function must not be called from a callback."
  []
  (GLFW/glfwPollEvents))

(defn post-empty-event
  "Posts an empty event from the current thread to the main thread event queue, causing wait-events or wait-events-timeout to return.

  If no windows exist, this function returns immediately. For synchronization of threads in applications that do not create windows, use your threading library of choice.

  ## Note
  * This function may be called from any thread."
  []
  (GLFW/glfwPostEmptyEvent))

(defn request-window-attention
  "Requests user attention to the specified window.

  This function requests user attention to the specified window. On platforms where this is not supported, attention is requested to the application as a whole.

  Once the user has given attention, usually by focusing the window or application, the system will end the request automatically.

  ## Note
  * This function must only be called from the main thread.
  * macOS: Attention is requested to the application as a whole, not the specific window.

  ## Parameters
  1. window - the window to request attention to"
  [window]
  (let [WINDOW (long window)]
    (GLFW/glfwRequestWindowAttention WINDOW)))

(defn restore-window
  "Restores the specified window if it was previously iconified (minimized) or maximized.

  If the window is already restored, this function does nothing.

  If the specified window is a full screen window, the resolution chosen for the window is restored on the selected monitor.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window to restore"
  [window]
  (let [WINDOW (long window)]
    (GLFW/glfwRestoreWindow window)))

(defn set-char-callback
  "Sets the character callback of the specified window, which is called when a Unicode character is input.

  The character callback is intended for Unicode text input. As it deals with characters, it is keyboard layout dependent, whereas SetKeyCallback is not. Characters do not map 1:1 to physical keys, as a key may produce zero, one or more characters. If you want to know whether a specific physical key was pressed or released, see the key callback instead.

  The character callback behaves as system text input normally does and will not be called if modifier keys are held down that would prevent normal text input on that platform, for example a Super (Command) key on macOS or Alt key on Windows. There is set-char-mods-callback that receives these events.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window whose callback to set
  2. callback - the new callback or nil to remove the currently set callback

  ## Returns
  the previously set callback, or nil if no callback was set"
  [window callback]
  (let [WINDOW (long window)
        CALLBACK (impl/nil->null (impl/fn->GLFWCharCallback callback))]
    (impl/GLFWCharCallback->fn (impl/null->nil (GLFW/glfwSetCharCallback WINDOW CALLBACK)))))

(defn set-char-mods-callback
  "Sets the character with modifiers callback of the specified window, which is called when a Unicode character is input regardless of what modifier keys are used.

  The character with modifiers callback is intended for implementing custom Unicode character input. For regular Unicode text input, see [set-char-callback]. Like the character callback, the character with modifiers callback deals with characters and is keyboard layout dependent. Characters do not map 1:1 to physical keys, as a key may produce zero, one or more characters. If you want to know whether a specific physical key was pressed or released, see [[set-key-callback]] instead.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window whose callback to set
  2. callback - the new callback or nil to remove the currently set callback

  ## Returns
  the previously set callback, or nil if no callback was set"
  [window callback]
  (let [WINDOW (long window)
        CALLBACK (impl/nil->null (impl/fn->GLFWCharModsCallback callback))]
    (impl/GLFWCharModsCallback->fn (impl/null->nil (GLFW/glfwSetCharModsCallback WINDOW CALLBACK)))))

(defn set-clipboard-string
  "Sets the system clipboard to the specified, UTF-8 encoded string.

  The specified string is copied before this function returns.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  window - the window that will own the clipboard contents
  string - a UTF-8 encoded string"
  [window string]
  (let [WINDOW (long window)
        STRING (impl/str->ByteBuffer string)]
    (GLFW/glfwSetClipboardString WINDOW STRING)
    (impl/free STRING)))

(defn set-cursor
  "Sets the cursor image to be used when the cursor is over the client area of the specified window.

  The set cursor will only be visible when the cursor mode of the window is cursor-normal.

  On some platforms, the set cursor may not be visible unless the window also has input focus.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window to set the system cursor for
  2. cursor - the cursor to set, or nil to switch back to the default arrow cursor"
  [window cursor]
  (let [WINDOW (long window)
        CURSOR (long (impl/nil->null cursor))]
    (GLFW/glfwSetCursor WINDOW CURSOR)))

(defn set-cursor-enter-callback
  "Sets the cursor boundary crossing callback of the specified window, which is called when the cursor enters or leaves the client area of the window.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window whose callback to set
  2. callback - the new callback or nil to remove the currently set callback

  ## Returns
  the previously set callback, or nil if no callback was set"
  [window callback]
  (let [WINDOW (long window)
        CALLBACK (impl/nil->null (impl/fn->GLFWCursorEnterCallback callback))]
    (impl/GLFWCursorEnterCallback->fn (impl/null->nil (GLFW/glfwSetCursorEnterCallback WINDOW CALLBACK)))))

(defn set-cursor-pos
  "Sets the [x y] position, in screen coordinates, of the cursor relative to the upper-left corner of the client area of the specified window.

  The window must have input focus. If the window does not have input focus when this function is called, it fails silently.

  Do not use this function to implement things like camera controls. GLFW already provides the cursor-disabled cursor mode that hides the cursor, transparently re-centers it and provides unconstrained cursor motion. See [[set-input-mode]] for more information.

  If the cursor mode is cursor-disabled then the cursor position is unconstrained and limited only by the minimum and maximum values of double.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the desired window
  2. [xpos ypos] - the desired x and ycoordinates, relative to the left edge of the client area"
  [window [xpos ypos]]
  (let [WINDOW (long window)
        XPOS (double xpos)
        YPOS (double ypos)]
    (GLFW/glfwSetCursorPos WINDOW XPOS YPOS)))

(defn set-cursor-pos-callback
  "Sets the cursor position callback of the specified window, which is called when the cursor is moved.

  Sets the cursor position callback of the specified window, which is called when the cursor is moved. The callback is provided with the position, in screen coordinates, relative to the upper-left corner of the client area of the window.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window whose callback to set
  2. callback - the new callback or nil to remove the currently set callback

  ## Returns
  the previously set callback, or nil if no callback was set"
  [window callback]
  (let [WINDOW (long window)
        CALLBACK (impl/nil->null (impl/fn->GLFWCursorPosCallback callback))]
    (impl/GLFWCursorPosCallback->fn (impl/null->nil (GLFW/glfwSetCursorPosCallback WINDOW CALLBACK)))))

(defn set-drop-callback
  "Sets the file drop callback of the specified window, which is called when one or more dragged files are dropped on the window.

  Because the path array and its strings may have been generated specifically for that event, they are not guaranteed to be valid after the callback has returned. If you wish to use them after the callback returns, you need to make a deep copy.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window whose callback to set
  2. callback - the new callback or nil to remove the currently set callback

  ## Returns
  the previously set callback, or nil if no callback was set"
  [window callback]
  (let [WINDOW (long window)
        CALLBACK (impl/nil->null (impl/fn->GLFWDropCallback callback))]
    (impl/GLFWDropCallback->fn (impl/null->nil (GLFW/glfwSetDropCallback WINDOW CALLBACK)))))

(defn set-error-callback
  "Sets the error callback, which is called with an error code and a human-readable description each time a GLFW error occurs.
  The error code is set before the callback is called. Calling GetError from the error callback will return the same value as the error code argument.

  The error callback is called on the thread where the error occurred. If you are using GLFW from multiple threads, your error callback needs to be written accordingly.

  Because the description string may have been generated specifically for that error, it is not guaranteed to be valid after the callback has returned. If you wish to use it after the callback returns, you need to make a copy.

  Once set, the error callback remains set even after the library has been terminated.

  ## Note
  * This function may be called before init.
  * This function must only be called from the main thread.

  ## Parameters
  1. callback - the new callback or nil to remove the currently set callback

  ## Returns
  the previously set callback, or nil if no callback was set"
  [callback]
  (let [CALLBACK (impl/nil->null (impl/fn->GLFWErrorCallback callback))]
    (impl/GLFWErrorCallback->fn (impl/null->nil (GLFW/glfwSetErrorCallback CALLBACK)))))

(defn set-framebuffer-size-callback
  "Sets the framebuffer resize callback of the specified window, which is called when the framebuffer of the specified window is resized.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window whose callback to set
  2. callback - the new callback or nil to remove the currently set callback

  ## Returns
  the previously set callback, or nil if no callback was set or the library had not been initialized"
  [window callback]
  (let [WINDOW (long window)
        CALLBACK (impl/nil->null (impl/fn->GLFWFramebufferSizeCallback callback))]
    (impl/GLFWFramebufferSizeCallback->fn (impl/null->nil (GLFW/glfwSetFramebufferSizeCallback WINDOW CALLBACK)))))

(defn set-gamma
  "Generates a 256-element gamma ramp from the specified exponent and then calls set-gamma-ramp with it.

  The value must be a finite number greater than zero.

  The software controlled gamma ramp is applied in addition to the hardware gamma correction, which today is usually an approximation of sRGB gamma. This means that setting a perfectly linear ramp, or gamma 1.0, will produce the default (usually sRGB-like) behavior.

  For gamma correct rendering with OpenGL or OpenGL ES, see the srgb-capable hint.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. monitor - the monitor whose gamma ramp to set
  2. gamma - the desired exponent"
  [monitor gamma]
  (let [MONITOR (long monitor)
        GAMMA (float gamma)]
    (GLFW/glfwSetGamma MONITOR GAMMA)))

(defn set-gamma-ramp
  "Sets the current gamma ramp for the specified monitor.

  The original gamma ramp for that monitor is saved by GLFW the first time this function is called and is restored by terminate.

  The software controlled gamma ramp is applied in addition to the hardware gamma correction, which today is usually an approximation of sRGB gamma. This means that setting a perfectly linear ramp, or gamma 1.0, will produce the default (usually sRGB-like) behavior.

  For gamma correct rendering with OpenGL or OpenGL ES, see the srgb-capable hint.

  ## Note
  * This function must only be called from the main thread.
  * Gamma ramp sizes other than 256 are not supported by all hardware
  * Windows: The gamma ramp size must be 256.
  * The specified gamma ramp is copied before this function returns.

  ## Parameters
  1. monitor - the monitor whose gamma ramp to set
  2. ramp - the gamma ramp to use"
  [monitor ramp]
  (let [MONITOR (long monitor)
        RAMP (impl/map->GLFWGammaRamp ramp)]
    (GLFW/glfwSetGamma MONITOR RAMP)))

(defn set-input-mode
  "Sets an input option for the specified window.

  If mode is cursor, the value must be one of the following cursor modes:
  * cursor-normal makes the cursor visible and behaving normally.
  * cursor-hidden makes the cursor invisible when it is over the client area of the window but does not restrict the cursor from leaving.
  * cursor-disabled hides and grabs the cursor, providing virtual and unlimited cursor movement. This is useful for implementing for example 3D camera controls.

  | Modes |
  | ------ |
  | ::cursor |
  | ::sticky-keys |
  | ::sticky-mouse-buttons |

  If mode is sticky-keys, the value must be either TRUE to enable sticky keys, or FALSE to disable it. If sticky keys are enabled, a key press will ensure that GetKey returns PRESS the next time it is called even if the key had been released before the call. This is useful when you are only interested in whether keys have been pressed but not when or in which order.

  If mode is sticky-mouse-buttons, the value must be either TRUE to enable sticky mouse buttons, or FALSE to disable it. If sticky mouse buttons are enabled, a mouse button press will ensure that GetMouseButton returns PRESS the next time it is called even if the mouse button had been released before the call. This is useful when you are only interested in whether mouse buttons have been pressed but not when or in which order.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window whose input mode to set
  2. mode - the input mode to set.
  3. value - the new value of the specified input mode"
  [window mode value]
  (let [WINDOW (long window)
        MODE (int (interpret mode))
        VALUE (int (interpret value))]
    (GLFW/glfwSetInputMode WINDOW MODE VALUE)))

(defn set-joystick-callback
  "Sets the joystick configuration callback, or removes the currently set callback.

  This is called when a joystick is connected to or disconnected from the system.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. callback - the new callback, or nil to remove the currently set callback

  ## Returns
  the previously set callback, or nil if no callback was set or the library had not been initialized"
  [callback]
  (let [CALLBACK (impl/nil->null (impl/fn->GLFWJoystickCallback callback))]
    (impl/GLFWJoystickCallback->fn (impl/null->nil (GLFW/glfwSetJoystickCallback CALLBACK)))))

(defn set-key-callback
  "Sets the key callback of the specified window, which is called when a key is pressed, repeated or released.

  The key functions deal with physical keys, with layout independent key tokens named after their values in the standard US keyboard layout. If you want to input text, use set-char-callback instead.

  When a window loses input focus, it will generate synthetic key release events for all pressed keys. You can tell these events from user-generated events by the fact that the synthetic ones are generated after the focus loss event has been processed, i.e. after the window focus callback has been called.

  The scancode of a key is specific to that platform or sometimes even to that machine. Scancodes are intended to allow users to bind keys that don't have a GLFW key token. Such keys have key set to key-unknown, their state is not saved and so it cannot be queried with get-key.

  Sometimes GLFW needs to generate synthetic key events, in which case the scancode may be zero.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window whose callback to set
  2. callback - the new callback or nil to remove the currently set callback

  ## Returns
  the previously set callback, or nil if no callback was set"
  [window callback]
  (let [WINDOW (long window)
        CALLBACK (impl/nil->null (impl/fn->GLFWKeyCallback callback))]
    (impl/GLFWKeyCallback->fn (impl/null->nil (GLFW/glfwSetKeyCallback WINDOW CALLBACK)))))

(defn set-monitor-callback
  "Sets the monitor configuration callback, or removes the currently set callback.

  This is called when a monitor is connected to or disconnected from the system.

  This function must only be called from the main thread.

  ## Parameters
  1. callback - the new callback, or nil to remove the currently set callback

  ## Returns
  the previously set callback, or nil if no callback was set or the library had not been initialized"
  [callback]
  (let [CALLBACK (impl/nil->null (impl/fn->GLFWMonitorCallback callback))]
    (impl/GLFWMonitorCallback->fn (impl/null->nil (GLFW/glfwSetMonitorCallback CALLBACK)))))

(defn set-mouse-button-callback
  "Sets the mouse button callback of the specified window, which is called when a mouse button is pressed or released.

  When a window loses input focus, it will generate synthetic mouse button release events for all pressed mouse buttons. You can tell these events from user-generated events by the fact that the synthetic ones are generated after the focus loss event has been processed, i.e. after the window focus callback has been called.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window whose callback to set
  2. callback - the new callback or nil to remove the currently set callback

  ## Returns
  the previously set callback, or nil if no callback was set"
  [window callback]
  (let [WINDOW (long window)
        CALLBACK (impl/nil->null (impl/fn->GLFWMouseButtonCallback callback))]
    (impl/GLFWMouseButtonCallback->fn (impl/null->nil (GLFW/glfwSetMouseButtonCallback WINDOW CALLBACK)))))

(defn set-scroll-callback
  "Sets the scroll callback of the specified window, which is called when a scrolling device is used.

  The scroll callback receives all scrolling input, like that from a mouse wheel or a touchpad scrolling area.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window whose callback to set
  2. callback - the new callback or nil to remove the currently set callback

  ## Returns
  the previously set callback, or nil if no callback was set"
  [window callback]
  (let [WINDOW (long window)
        CALLBACK (impl/nil->null (impl/fn->GLFWScrollCallback callback))]
    (impl/GLFWScrollCallback->fn (impl/null->nil (GLFW/glfwSetScrollCallback WINDOW CALLBACK)))))

(defn set-time
  "Sets the value of the GLFW timer.

  It then continues to count up from that value. The value must be a positive finite number less than or equal to 18446744073.0, which is approximately 584.5 years.

  ## Note
  * This function may be called from any thread.
  * Reading and writing of the internal timer offset is not atomic, so it needs to be externally synchronized with calls to get-time.

  ## Parameters
  1. time - the new value, in seconds"
  [time]
  (let [TIME (double time)]
    (GLFW/glfwSetTime TIME)))

(defn set-window-aspect-ratio
  "Sets the required aspect ratio of the client area of the specified window.

  If the window is full screen, the aspect ratio only takes effect once it is made windowed. If the window is not resizable, this function does nothing.

  The aspect ratio is specified as a numerator and a denominator and both values must be greater than zero. For example, the common 16:9 aspect ratio is specified as 16 and 9, respectively.

  If the numerator and denominator is set to ::dont-care then the aspect ratio limit is disabled.

  The aspect ratio is applied immediately to a windowed mode window and may cause it to be resized.

  This function must only be called from the main thread.

  ## Parameters
  1. window - the window to set limits for
  2. numer - the numerator of the desired aspect ratio, or ::dont-care
  3. denom - the denominator of the desired aspect ratio, or ::dont-care"
  [window numer denom]
  (let [WINDOW (long window)
        NUMER (int numer)
        DENOM (int denom)]
    (GLFW/glfwSetWindowAspectRatio WINDOW NUMER DENOM)))

(defn set-window-attrib
  "Sets an attribute of the specified window.

  Some of these attributes are ignored for full screen windows. The new value will take effect if the window is later made windowed.

  Some of these attributes are ignored for windowed mode windows. The new value will take effect if the window is later made full screen.

  Calling get-window-attrib will always return the latest value, even if that value is ignored by the current mode of the window. One of:

  | Attributes |
  | ------ |
  | ::decorated |
  | ::resizable |
  | ::floating |
  | ::auto-iconify |

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window to set the attribute for
  2. attrib - the attribute to set.
  3. value - the value to set"
  [window attrib value]
  (let [WINDOW (long window)
        ATTRIB (int (interpret attrib))
        VALUE (int (interpret value))]
    (GLFW/glfwSetWindowAttrib WINDOW ATTRIB VALUE)))

(defn set-window-close-callback
  "Sets the close callback of the specified window, which is called when the user attempts to close the window, for example by clicking the close widget in the title bar.

  The close flag is set before this callback is called, but you can modify it at any time with set-window-should-close.

  The close callback is not triggered by destroy-window.

  ## Note
  * This function must only be called from the main thread.
  * macOS: Selecting Quit from the application menu will trigger the close callback for all windows.

  ## Parameters
  1. window - the window whose callback to set
  2. callback - the new callback or nil to remove the currently set callback

  ## Returns
  the previously set callback, or nil if no callback was set or the library had not been initialized"
  [window callback]
  (let [WINDOW (long window)
        CALLBACK (impl/nil->null (impl/fn->GLFWWindowCloseCallback callback))]
    (impl/GLFWWindowCloseCallback->fn (impl/null->nil (GLFW/glfwSetWindowCloseCallback WINDOW CALLBACK)))))

(defn set-window-focus-callback
  "Sets the focus callback of the specified window, which is called when the window gains or loses input focus.

  After the focus callback is called for a window that lost input focus, synthetic key and mouse button release events will be generated for all such that had been pressed. For more information, see [[set-key-callback]] and [[set-mouse-button-callback]].

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window whose callback to set
  2. callback - the new callback or nil to remove the currently set callback

  ## Returns
  the previously set callback, or nil if no callback was set or the library had not been initialized"
  [window callback]
  (let [WINDOW (long window)
        CALLBACK (impl/nil->null (impl/fn->GLFWWindowFocusCallback callback))]
    (impl/GLFWWindowFocusCallback->fn (impl/null->nil (GLFW/glfwSetWindowFocusCallback WINDOW CALLBACK)))))

(defn set-window-icon
  "Sets the icon for the specified window.

  This function sets the icon of the specified window. If passed an array of candidate images, those of or closest to the sizes desired by the system are selected. If no images are specified, the window reverts to its default icon.

  The desired image sizes varies depending on platform and system settings. The selected images will be rescaled as needed. Good sizes include 16x16, 32x32 and 48x48.

  The specified image data is copied before this function returns.

  ## Note
  * macOS: The GLFW window has no icon, as it is not a document window, so this function does nothing. The dock icon will be the same as the application bundle's icon. For more information on bundles, see the Bundle Programming Guide in the Mac Developer Library.
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window whose icon to set
  2. images - the images to create the icon from. This is ignored if count is zero."
  [window images]
  (let [WINDOW (long window)
        IMAGES (impl/seq->GLFWImage$Buffer images)]
    (GLFW/glfwSetWindowIcon WINDOW IMAGES)
    (impl/free IMAGES)))

(defn set-window-iconify-callback
  "Sets the iconification callback of the specified window, which is called when the window is iconified or restored."
  [window callback]
  (let [WINDOW (long window)
        CALLBACK (impl/fn->GLFWWindowIconifyCallback callback)]
    (impl/GLFWWindowIconifyCallback->fn (GLFW/glfwSetWindowIconifyCallback WINDOW CALLBACK))))

(defn set-window-maximize-callback
  "Sets the maximization callback of the specified window, which is called when the window is maximized or restored.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window whose callback to set
  2. callback - the new callback or nil to remove the currently set callback

  ## Returns
  the previously set callback, or nil if no callback was set or the library had not been initialized"
  [window callback]
  (let [WINDOW (long window)
        CALLBACK (impl/nil->null (impl/fn->GLFWWindowMaximizeCallback callback))]
    (impl/GLFWWindowMaximizeCallback->fn (impl/null->nil (GLFW/glfwSetWindowMaximizeCallback WINDOW CALLBACK)))))

(defn set-window-monitor
  "Sets the mode, monitor, video mode and placement of a window.

  This function sets the monitor that the window uses for full screen mode or, if the monitor is nil, makes it windowed mode.

  When setting a monitor, this function updates the width, height and refresh rate of the desired video mode and switches to the video mode closest to it. The window position is ignored when setting a monitor.

  When the monitor is nil, the position, width and height are used to place the window client area. The refresh rate is ignored when no monitor is specified.

  If you only wish to update the resolution of a full screen window or the size of a windowed mode window, see [[set-window-size]].

  When a window transitions from full screen to windowed mode, this function restores any previous window settings such as whether it is decorated, floating, resizable, has size or aspect ratio limits, etc.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  window - the window whose monitor, size or video mode to set
  monitor - the desired monitor, or nil to set windowed mode
  [xpos ypos] - the desired x and y coordinates of the upper-left corner of the client area
  [width height] - the desired width and height, in screen coordinates, of the client area or video mode
  refresh-rate - the desired refresh rate, in Hz, of the video mode, or ::dont-care"
  [window monitor [xpos ypos] [width height] refresh-rate]
  (let [WINDOW (long window)
        MONITOR (long (impl/nil->null monitor))
        XPOS (int xpos)
        YPOS (int ypos)
        WIDTH (int width)
        HEIGHT (int height)
        REFRESH-RATE (int refresh-rate)]
    (GLFW/glfwSetWindowMonitor WINDOW MONITOR XPOS YPOS WIDTH HEIGHT REFRESH-RATE)))

(defn set-window-pos
  "Sets the position, in screen coordinates, of the upper-left corner of the client area of the specified windowed mode window.

  If the window is a full screen window, this function does nothing.

  Do not use this function to move an already visible window unless you have very good reasons for doing so, as it will confuse and annoy the user.

  The window manager may put limits on what positions are allowed. GLFW cannot and should not override these limits.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window to query
  2. xpos - the x-coordinate of the upper-left corner of the client area
  3. ypos - the y-coordinate of the upper-left corner of the client area"
  [window xpos ypos]
  (let [WINDOW (long window)
        XPOS (int xpos)
        YPOS (int ypos)]
    (GLFW/glfwSetWindowPos WINDOW XPOS YPOS)))

(defn set-window-pos-callback
  "Sets the position callback of the specified window, which is called when the window is moved.

  The callback is provided with the screen position of the upper-left corner of the client area of the window.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window whose callback to set
  2. callback - the new callback or nil to remove the currently set callback

  ## Returns
  the previously set callback, or nil if no callback was set or the library had not been initialized"
  [window callback]
  (let [WINDOW (long window)
        CALLBACK (impl/nil->null (impl/fn->GLFWWindowPosCallback callback))]
    (impl/GLFWWindowPosCallback->fn (impl/null->nil (GLFW/glfwSetWindowPosCallback WINDOW CALLBACK)))))

(defn set-window-refresh-callback
  "Sets the refresh callback of the specified window, which is called when the client area of the window needs to be redrawn, for example if the window has been exposed after having been covered by another window.

  On compositing window systems such as Aero, Compiz or Aqua, where the window contents are saved off-screen, this callback may be called only very infrequently or never at all.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window whose callback to set
  2. callback - the new callback or nil to remove the currently set callback

  ## Returns
  the previously set callback, or nil if no callback was set or the library had not been initialized"
  [window callback]
  (let [WINDOW (long window)
        CALLBACK (impl/nil->null (impl/fn->GLFWWindowRefreshCallback callback))]
    (impl/GLFWWindowRefreshCallback->fn (impl/null->nil (GLFW/glfwSetWindowRefreshCallback WINDOW CALLBACK)))))

(defn set-window-should-close
  "Sets the value of the close flag of the specified window.

  This can be used to override the user's attempt to close the window, or to signal that it should be closed.

  ## Note
  * This function may be called from any thread. Access is not synchronized.

  ## Parameters
  1. window - the window whose flag to change
  2. value - the new value"
  [window value]
  (let [WINDOW (long window)
        VALUE (boolean value)]
    (GLFW/glfwSetWindowShouldClose WINDOW VALUE)))

(defn set-window-size
  "Sets the size, in pixels, of the client area of the specified window.

  For full screen windows, this function updates the resolution of its desired video mode and switches to the video mode closest to it, without affecting the window's context. As the context is unaffected, the bit depths of the framebuffer remain unchanged.

  If you wish to update the refresh rate of the desired video mode in addition to its resolution, see [[set-window-monitor]].

  The window manager may put limits on what sizes are allowed. GLFW cannot and should not override these limits.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  window - the window to resize
  [width height] - the desired width and height, in screen coordinates, of the window client area"
  [window [width height]]
  (let [WINDOW (long window)
        WIDTH (int width)
        HEIGHT (int height)]
    (GLFW/glfwSetWindowSize WINDOW WIDTH HEIGHT)))

(defn set-window-size-callback
  "Sets the size callback of the specified window, which is called when the window is resized.

  The callback is provided with the size, in screen coordinates, of the client area of the window.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window whose callback to set
  2. callback - the new callback or nil to remove the currently set callback

  ## Returns
  the previously set callback, or nil if no callback was set or the library had not been initialized"
  [window callback]
  (let [WINDOW (long window)
        CALLBACK (impl/nil->null (impl/fn->GLFWWindowSizeCallback callback))]
    (impl/GLFWWindowSizeCallback->fn (impl/null->nil (GLFW/glfwSetWindowSizeCallback WINDOW CALLBACK)))))

(defn set-window-size-limits
  "Sets the minimum [w h] and maximum [w h] size limits of the client area of the specified window.

  If the window is full screen, the size limits only take effect if once it is made windowed. If the window is not resizable, this function does nothing.

  The size limits are applied immediately to a windowed mode window and may cause it to be resized.

  The maximum dimensions must be greater than or equal to the minimum dimensions and all must be greater than or equal to zero.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window to set limits for
  2. [minwidth minheight] - the minimum dimensions, in screen coordinates, of the client area, or ::dont-care
  3. [maxwidth maxheight] - the maximum dimensions, in screen coordinates, of the client area, or ::dont-care"
  [window [min-width min-height] [max-width max-height]]
  (let [WINDOW (long window)
        MIN-WIDTH (int min-width)
        MIN-HEIGHT (int min-height)
        MAX-WIDTH (int max-width)
        MAX-HEIGHT (int max-height)]
    (GLFW/glfwSetWindowSizeLimits WINDOW MIN-WIDTH MIN-HEIGHT MAX-WIDTH MAX-HEIGHT)))

(defn set-window-title
  "Sets the window title, encoded as UTF-8, of the specified window.

  ## Note
  * This function must only be called from the main thread.
  * macOS: The window title will not be updated until the next time you process events.

  ## Parameters
  1. window - the window whose title to change
  2. title - the UTF-8 encoded window title"
  [window title]
  (let [WINDOW (long window)
        TITLE (impl/str->ByteBuffer title)]
    (GLFW/glfwSetWindowTitle WINDOW TITLE)
    (impl/free TITLE)))

(defn set-window-user-pointer
  "Sets the user-defined pointer of the specified window.

  The current value is retained until the window is destroyed. The initial value is 0.

  ## Note
  * This function may be called from any thread. Access is not synchronized.

  ## Parameters
  1. window - the window whose pointer to set
  2. pointer - the new value"
  [window pointer]
  (let [WINDOW (long window)
        POINTER (long pointer)]
    (GLFW/glfwSetWindowUserPointer WINDOW POINTER)))

(defn show-window
  "Makes the specified window visible if it was previously hidden.

  If the window is already visible or is in full screen mode, this function does nothing.

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. window - the window to make visible"
  [window]
  (let [WINDOW (long window)]
    (GLFW/glfwShowWindow WINDOW)))

(defn swap-buffers
  "Swaps the front and back buffers of the specified window when rendering with OpenGL or OpenGL ES.

  If the swap interval is greater than zero, the GPU driver waits the specified number of screen updates before swapping the buffers.

  The specified window must have an OpenGL or OpenGL ES context. Specifying a window without a context will generate a no-window-context error.

  This function does not apply to Vulkan. If you are rendering with Vulkan, vkQueuePresentKHR instead.

  EGL: The context of the specified window must be current on the calling thread.

  ## Note
  * This function may be called from any thread.

  ## Parameters
  1. window - the window whose buffers to swap"
  [window]
  (let [WINDOW (long window)]
    (GLFW/glfwSwapBuffers WINDOW)))

(defn swap-interval
  "Sets the swap interval for the current OpenGL or OpenGL ES context, i.e.  the number of screen updates to wait from the time SwapBuffers was called before swapping the buffers and returning.

  This is sometimes called vertical synchronization, vertical retrace synchronization or just vsync.

  Contexts that support either of the wgl-ext-swap-control-tear and glx-ext-swap-control-tear extensions also accept negative swap intervals, which allow the driver to swap even if a frame arrives a little bit late. You can check for the presence of these extensions using ExtensionSupported. For more information about swap tearing, see the extension specifications.

  A context must be current on the calling thread. Calling this function without a current context will cause a no-current-context error.

  This function does not apply to Vulkan. If you are rendering with Vulkan, see the present mode of your swapchain instead.

  ## Note
  * This function may be called from any thread.
  * This function is not called during window creation, leaving the swap interval set to whatever is the default on that platform. This is done because some swap interval extensions used by GLFW do not allow the swap interval to be reset to zero once it has been set to a non-zero value.
  * Some GPU drivers do not honor the requested swap interval, either because of a user setting that overrides the application's request or due to bugs in the driver.

  ## Parameters
  1. interval - the minimum number of screen updates to wait for until the buffers are swapped by SwapBuffers"
  [interval]
  (let [INTERVAL (int interval)]
    (GLFW/glfwSwapInterval interval)))

(defn terminate
  "Destroys all remaining windows and cursors, restores any modified gamma ramps and frees any other allocated resources.

  Once this function is called, you must again call [[init]] successfully before you will be able to use most GLFW functions.

  If GLFW has been successfully initialized, this function should be called before the application exits. If initialization fails, there is no need to call this function, as it is called by init before it returns failure.

  ## Note
  * This function may be called before init.
  * This function must only be called from the main thread.
  * This function must not be called from a callback.
  * No window's context may be current on another thread when this function is called."
  []
  (GLFW/glfwTerminate))

(defn wait-events
  "Waits until events are queued and processes them.

  This function puts the calling thread to sleep until at least one event is available in the event queue. Once one or more events are available, it behaves exactly like poll-events, i.e. the events in the queue are processed and the function then returns immediately. Processing events will cause the window and input callbacks associated with those events to be called.

  Since not all events are associated with callbacks, this function may return without a callback having been called even if you are monitoring all callbacks.

  On some platforms, a window move, resize or menu operation will cause event processing to block. This is due to how event processing is designed on those platforms. You can use the window refresh callback to redraw the contents of your window when necessary during such operations.

  On some platforms, certain callbacks may be called outside of a call to one of the event processing functions.

  If no windows exist, this function returns immediately. For synchronization of threads in applications that do not create windows, use your threading library of choice.

  Event processing is not required for joystick input to work.

  ## Note
  * This function must only be called from the main thread.
  * This function must not be called from a callback."
  []
  (GLFW/glfwWaitEvents))

(defn wait-events-timeout
  "Waits with timeout until events are queued and processes them.

  This function puts the calling thread to sleep until at least one event is available in the event queue, or until the specified timeout is reached. If one or more events are available, it behaves exactly like PollEvents, i.e. the events in the queue are processed and the function then returns immediately. Processing events will cause the window and input callbacks associated with those events to be called.

  The timeout value must be a positive finite number.

  Since not all events are associated with callbacks, this function may return without a callback having been called even if you are monitoring all callbacks.

  On some platforms, a window move, resize or menu operation will cause event processing to block. This is due to how event processing is designed on those platforms. You can use the window refresh callback to redraw the contents of your window when necessary during such operations.

  On some platforms, certain callbacks may be called outside of a call to one of the event processing functions.

  If no windows exist, this function returns immediately. For synchronization of threads in applications that do not create windows, use your threading library of choice.

  Event processing is not required for joystick input to work.

  ## Note
  * This function must only be called from the main thread.
  * This function must not be called from a callback.

  ## Parameters
  1. timeout - the maximum amount of time, in seconds, to wait"
  [timeout]
  (let [TIMEOUT (double timeout)]
    (GLFW/glfwWaitEventsTimeout TIMEOUT)))

(defn window-hint
  "Sets hints for the next call to [[create-window]].

  The hints, once set, retain their values until changed by a call to [[window-hint]] or [[default-window-hints]], or until the library is terminated.

  This function does not check whether the specified hint values are valid. If you set hints to invalid values this will instead be reported by the next call to [[create-window]].

  | Hints | Defaults | Supported Values |
  | ------ | ------ | ------ |
  ::resizable |	::true | ::true or ::false
  ::visible |	::true | ::true or ::false
  ::decorated |	::true | ::true or ::false
  ::focused |	::true | ::true or ::false
  ::auto-iconify | ::true | ::true or ::false
  ::floating | ::false | ::false or ::false
  ::maximized |	::false | ::false or ::false
  ::center-cursor |	::true | ::true or ::false
  ::red-bits | 8 | 0 to Integer.MAX_VALUE or ::dont-care
  ::green-bits | 8 | 0 to Integer.MAX_VALUE or ::dont-care
  ::blue-bits |	8 | 0 to Integer.MAX_VALUE or ::dont-care
  ::alpha-bits | 8 | 0 to Integer.MAX_VALUE or ::dont-care
  ::depth-bits | 24 | 0 to Integer.MAX_VALUE or ::dont-care
  ::stencil-bits | 8 | 0 to Integer.MAX_VALUE or ::dont-care
  ::accum-red-bits | 0 | 0 to Integer.MAX_VALUE or ::dont-care
  ::accum-green-bits | 0 | 0 to Integer.MAX_VALUE or ::dont-care
  ::accum-blue-bits |	0 | 0 to Integer.MAX_VALUE or ::dont-care
  ::accum-alpha-bits | 0 | 0 to Integer.MAX_VALUE or ::dont-care
  ::aux-buffers |	0 | 0 to Integer.MAX_VALUE
  ::samples |	0 | 0 to Integer.MAX_VALUE
  ::refresh-rate | ::dont-care | 0 to Integer.MAX_VALUE or ::dont-care
  ::stereo | ::false | ::true or ::false
  ::srgb-capable | ::false | ::true or ::false
  ::doublebuffer | ::true | ::true or ::false
  ::client-api | ::opengl-api | ::no-api ::opengl-api ::opengl-es-api
  ::context-creation-api | ::native-context-api |  ::native-context-api ::egl-context-api ::osmesa-context-api
  ::context-version-major |	1 | any valid major version number of the chosen client api
  ::context-version-minor |	0 | any valid minor version number of the chosen client api
  ::context-robustness | ::no-robustness | ::no-robustness ::no-reset-notification ::lose-context-on-reset
  ::context-release-behavior | ::any-release-behavior | ::any-release-behavior ::release-behavior-flush ::release-behavior-none
  ::context-no-error | ::false | ::true or ::false
  ::opengl-forward-compat |	::false | ::true or ::false
  ::opengl-debug-context | ::false | ::true or ::false
  ::opengl-profile | ::opengl-any-profile | ::opengl-any-profile ::opengl-core-profile ::opengl-compat-profile
  ::cocoa-retina-framebuffer | ::true | ::true or ::false
  ::cocoa-frame-autosave | ::false | ::true or ::false
  ::cocoa-graphics-switching | ::false | ::true or ::false

  ## Note
  * This function must only be called from the main thread.

  ## Parameters
  1. hint - the window hint to set.
  2. value - the new value of the window hint"
  [hint value]
  (let [HINT (int (interpret hint))
        VALUE (int (interpret value))]
    (GLFW/glfwWindowHint HINT VALUE)))

(defn window-should-close
  "Returns the value of the close flag of the specified window.

  ## Note
  * This function may be called from any thread."
  [window]
  (let [WINDOW (long window)]
    (GLFW/glfwWindowShouldClose WINDOW)))
