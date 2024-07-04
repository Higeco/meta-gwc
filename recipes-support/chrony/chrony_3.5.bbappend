do_install_append() {
    # System V init scripit and systemd
    rm -r ${D}${sysconfdir}/init.d
    rm -r ${D}${systemd_unitdir}/system
    rm -r ${D}/lib
}

SYSTEMD_PACKAGES = ""
SYSTEMD_SERVICE_${PN} = ""
