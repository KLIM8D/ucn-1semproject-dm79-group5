.PHONY: all clean run

SOURCES = \
	ControlLayer/ProductCtrl \
	ControlLayer/ProductLocationCtrl \
	ModelLayer/Business \
	ModelLayer/Customer \
	ModelLayer/CustomerContainer \
	ModelLayer/Discount \
	ModelLayer/Order \
	ModelLayer/OrderContainer \
	ModelLayer/OrderLine \
	ModelLayer/OrderStatus \
	ModelLayer/Person \
	ModelLayer/PersonContainer \
	ModelLayer/Product \
	ModelLayer/ProductCategory \
	ModelLayer/ProductCategoryContainer \
	ModelLayer/ProductContainer \
	ModelLayer/ProductGroup \
	ModelLayer/ProductGroupContainer \
	ModelLayer/ProductGroupItem \
	ModelLayer/ProductLocation \
	ModelLayer/ProductLocationContainer \
	ModelLayer/ProductPhysicalAvail \
	ModelLayer/Purchase \
	ModelLayer/PurchaseContainer \
	ModelLayer/SalesAssistant \
	ModelLayer/SalesAssistantContainer \
	ModelLayer/SerialNumber \
	TUILayer/MainMenuUI \
	TUILayer/CustomerMenuUI \
	TUILayer/GlobalUI \
	TUILayer/MainMenuUI \
	TUILayer/SalesMenuUI \
	TUILayer/EconomyMenuUI \
	TUILayer/LoginUI \
	TUILayer/RentalMenuUI \
	TUILayer/StockMenuUI \
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
