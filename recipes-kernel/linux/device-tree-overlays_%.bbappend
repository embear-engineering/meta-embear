do_install:append() {
    # Make sure the overlays are accessible under overlays as well
    ln -s devicetree ${D}/boot/overlays
    # overlays that we want to be applied during boot time
    overlays=
    for dtbo in ${TEZI_EXTERNAL_KERNEL_DEVICETREE_BOOT}; do
        if [ ! -e ${D}/boot/devicetree/$dtbo ]; then
            bbfatal "$dtbo is not installed in your boot filesystem, please make sure it's in TEZI_EXTERNAL_KERNEL_DEVICETREE or being provided by virtual/dtb."
        fi
        overlays="$overlays $dtbo"
    done

    echo "fdt_overlays=$(echo $overlays)" > ${D}/boot/overlays.txt
}

FILES:${PN} = "/boot"
