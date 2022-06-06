############################################################################
##
## Copyright (C) 2021 The Qt Company Ltd.
## Contact: https://www.qt.io/licensing/
##
## This file is part of the Boot to Qt meta layer.
##
## $QT_BEGIN_LICENSE:GPL$
## Commercial License Usage
## Licensees holding valid commercial Qt licenses may use this file in
## accordance with the commercial license agreement provided with the
## Software or, alternatively, in accordance with the terms contained in
## a written agreement between you and The Qt Company. For licensing terms
## and conditions see https://www.qt.io/terms-conditions. For further
## information use the contact form at https://www.qt.io/contact-us.
##
## GNU General Public License Usage
## Alternatively, this file may be used under the terms of the GNU
## General Public License version 3 or (at your option) any later version
## approved by the KDE Free Qt Foundation. The licenses are as published by
## the Free Software Foundation and appearing in the file LICENSE.GPL3
## included in the packaging of this file. Please review the following
## information to ensure the GNU General Public License requirements will
## be met: https://www.gnu.org/licenses/gpl-3.0.html.
##
## $QT_END_LICENSE$
##
############################################################################

SUMMARY = "Boot2Qt Embear Image"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=c79ff39f19dfec6d293b95dea7b07891"

inherit populate_sdk_qt6_base

DEPLOY_CONF_TYPE = "Boot2Qt $QT_VERSION"

IMAGE_FEATURES += "\
        package-management \
        ssh-server-dropbear \
        tools-debug \
        tools-profile \
        debug-tweaks \
        hwcodecs \
        "

inherit core-image deploy-buildinfo
inherit consistent_timestamps

# add some extra space to the device images
IMAGE_ROOTFS_EXTRA_SPACE = "100000"

# Set Packages here to avoid commercial package groups
MACHINE_EXTRA_INSTALL ?= ""
BASE_PACKAGES = " \
        kernel-modules \
        linux-firmware \
        ca-certificates \
        liberation-fonts \
        ttf-devanagari \
        ttf-opensans \
        ttf-dejavu-common \
        ttf-dejavu-sans \
        ttf-freefont-mono \
        ttf-tlwg \
        otf-noto \
        tzdata \
        tzdata-americas \
        tzdata-asia \
        tzdata-europe \
        connman \
        rng-tools \
        udev-extraconf \
        ${@bb.utils.contains("DISTRO_FEATURES", "wayland", "weston weston-init weston-examples", "", d)} \
        ${@bb.utils.contains("DISTRO_FEATURES", "pulseaudio", "pulseaudio-server pulseaudio-misc", "", d)} \
        ${MACHINE_EXTRA_INSTALL} \
        "

MACHINE_GSTREAMER_1_0_PLUGIN ?= ""
GSTREAMER_PACKAGES = "\
        gstreamer1.0-meta-base \
        gstreamer1.0-meta-video \
        gstreamer1.0-meta-audio \
        gstreamer1.0-plugins-base-meta \
        gstreamer1.0-plugins-good-meta \
        gstreamer1.0-plugins-ugly-meta \
        gstreamer1.0-plugins-bad-meta \
        gstreamer1.0-libav \
        ${MACHINE_GSTREAMER_1_0_PLUGIN} \
        "

TOOLS_PACKAGES = "\
        alsa-utils-amixer \
        binutils \
        binutils-symlinks \
        connman-client \
        gcc-sanitizers \
        e2fsprogs-resize2fs \
        htop \
        i2c-tools \
        iproute2 \
        ldd \
        mtd-utils \
        openssh-sftp-server \
        parted \
        procps \
        rsync \
        tslib-calibrate \
        ${@bb.utils.contains("DISTRO_FEATURES", "systemd", "systemd-analyze", "", d)} \
        "

IMAGE_INSTALL += "\
    ${BASE_PACKAGES} \
    ${GSTREAMER_PACKAGES} \
    ${TOOLS_PACKAGES} \
    ${@bb.utils.contains("DISTRO_FEATURES", "gstreamer", "${GSTREAMER_PACKAGES}", "", d)} \
    packagegroup-qt6-modules \
    qdb \
    "
