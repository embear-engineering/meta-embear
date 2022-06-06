FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

do_install() {
    sed -e 's/@@KERNEL_BOOTCMD@@/${KERNEL_BOOTCMD}/;s/@@KERNEL_IMAGETYPE@@/${KERNEL_IMAGETYPE}/' \
        "${WORKDIR}/boot.cmd.in" > boot.cmd
    mkimage -T script -C none -n "Distro boot script" -d boot.cmd boot.scr

    install -D -m 0644 boot.scr ${D}/boot/boot.scr
}

FILES:${PN} = "/boot"
