.PHONY: all clean run

SOURCES = \
	ControlLayer/ProductCtrl \
	ControlLayer/ProductLocationCtrl \
	ControlLayer/PurchaseCtrl \
	ControlLayer/SalesAssistantCtrl \
	ModelLayer/Business \
	ModelLayer/Customer \
	ModelLayer/CustomerContainer \
	ModelLayer/Discount \
	ModelLayer/Order \
	ModelLayer/OrderContainer \
	ModelLayer/OrderLine \
	ModelLayer/OrderStatus \
	ModelLayer/OrderStatusContainer \
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
	TUILayer/CustomerMenuUI \
	TUILayer/EconomyMenuUI \
	TUILayer/GlobalUI \
	TUILayer/LoginUI \
	TUILayer/MainMenuUI \
	TUILayer/RentalMenuUI \
	TUILayer/SalesMenuUI \
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
