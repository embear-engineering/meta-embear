FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
    file://bootcount.cfg \
    file://bootlimit.cfg \
"
