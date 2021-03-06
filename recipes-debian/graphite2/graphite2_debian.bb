SUMMARY = "Font rendering engine for Complex Scripts"
DESCRIPTION = "Graphite is a system that can be used to create and use "smart fonts" capable\n\
of displaying writing systems with various complex behaviors, such as:\n\
contextual shaping, ligatures, reordering, split glyphs, bidirectionality,\n\
stacking diacritics and complex positioning.\n\
.\n\
This library was designed and developed by the NRSI (Non-Roman Script\n\
Initiative) within SIL International (www.sil.org) to act as a complement to\n\
other smart font rendering technologies with limited practical local\n\
extensibility. Its purpose is to help meet the needs of a very large number\n\
of "minority language" communities for local extensibility of complex script\n\
behaviors.\n\
.\n\
The behavior of the rendering engine for a given writing system is specified\n\
through extra tables added to a TrueType font.  These tables are generated by\n\
compiling a GDL (Graphite Description Language) source file into a font using\n\
grcompiler."

PR = "r1"

inherit debian-package
PV = "1.3.6"

LICENSE = "LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=acba2ba259d936c324b90ab679e6b901"

# In case build directory has freetype-native but not freetype,
# cmake will use freetype libraries and headers from native.
# Disable building for tests/examples to avoid the error.
SRC_URI += "file://dont-build-tests-examples.patch"

inherit cmake lib_package

do_install_append() {
	# Follow debian/libgraphite2-3.links
	ln -s libgraphite2.so.3 ${D}${libdir}/libgraphite2.so.2.0.0

	# Remove unnecessary files
	rm ${D}${datadir}/${DPN}/*.cmake
}

DEBIANNAME_${PN}-dbg = "lib${PN}-3-dbg"

BBCLASSEXTEND = "native"
