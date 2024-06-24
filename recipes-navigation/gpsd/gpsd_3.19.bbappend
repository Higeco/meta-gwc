do_install_append() {
    # Remove files for init.d and systemd
    rm -r ${D}/${sysconfdir}/init.d
    rm -r ${D}/${systemd_unitdir}/system/
    rm -r ${D}/lib/systemd
}

SYSTEMD_SERVICE_${PN} = ""
