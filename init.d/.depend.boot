TARGETS = mountkernfs.sh fake-hwclock hostname.sh udev keyboard-setup mountdevsubfs.sh console-setup networking mountall.sh mountall-bootclean.sh urandom mountnfs.sh mountnfs-bootclean.sh x11-common kbd alsa-utils checkroot.sh plymouth-log bootmisc.sh udev-mtab checkroot-bootclean.sh mtab.sh procps checkfs.sh kmod
INTERACTIVE = udev keyboard-setup console-setup kbd checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
mountdevsubfs.sh: mountkernfs.sh udev
console-setup: mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh kbd
networking: mountkernfs.sh mountall.sh mountall-bootclean.sh urandom
mountall.sh: checkfs.sh checkroot-bootclean.sh
mountall-bootclean.sh: mountall.sh
urandom: mountall.sh mountall-bootclean.sh
mountnfs.sh: mountall.sh mountall-bootclean.sh networking
mountnfs-bootclean.sh: mountall.sh mountall-bootclean.sh mountnfs.sh
x11-common: mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh
kbd: mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh
alsa-utils: mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh
checkroot.sh: fake-hwclock mountdevsubfs.sh hostname.sh keyboard-setup
plymouth-log: mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh
bootmisc.sh: mountall-bootclean.sh checkroot-bootclean.sh mountall.sh mountnfs.sh mountnfs-bootclean.sh udev
udev-mtab: udev mountall.sh mountall-bootclean.sh
checkroot-bootclean.sh: checkroot.sh
mtab.sh: checkroot.sh
procps: mountkernfs.sh mountall.sh mountall-bootclean.sh udev
checkfs.sh: checkroot.sh mtab.sh
kmod: checkroot.sh
