# Overwrite buggy do_install
do_install() {
    autotools_do_install

    # Rename DSP Firmware into hifi4.bin and remove unneeded binary
    mv ${D}${libdir}/firmware/imx/dsp/${HIFI4_BIN} ${D}${libdir}/firmware/imx/dsp/hifi4.bin
    find ${D}${libdir}/firmware/imx/dsp -name hifi4_*.bin -exec rm {} \;

    return 0
}

