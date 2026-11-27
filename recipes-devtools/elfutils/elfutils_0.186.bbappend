
# With recent curl versions we get a few deprecation warnings that will break the build,
# we silence them here therefore
CFLAGS:append = " -Wno-error=deprecated-declarations"
