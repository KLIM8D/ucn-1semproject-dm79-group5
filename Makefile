.PHONY: all clean run

SOURCES = \
	ControlLayer/ProductCtrl \
	ModelLayer/Business \
	ModelLayer/Discount \
	ModelLayer/Person \
	ModelLayer/Product \
	ModelLayer/ProductCategory \
	ModelLayer/ProductContainer \
	ModelLayer/ProductLocation \
	ModelLayer/ProductLocationContainer \
	ModelLayer/ProductPhysicalAvail \
	ModelLayer/SalesAssistant \
	TUILayer/MainMenuUI \
	Program

SRC = $(addprefix src/, $(addsuffix .java, $(SOURCES)))
BIN = $(addprefix bin/, $(addsuffix .class, $(SOURCES)))

all: ${BIN}

clean:
	-rm -f ${BIN};

${BIN}: ${SRC}
	mkdir -p bin/ControlLayer;
	mkdir -p bin/ModelLayer;
	mkdir -p bin/TUILayer;
	javac -d bin ${SRC};

run: all
	java -cp bin Program;
