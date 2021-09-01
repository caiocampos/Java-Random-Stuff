
enum DataType {
	NO_TYPE,
	A_TYPE
}

abstract class TypedData {
	public static DataType getType() {
		return DataType.NO_TYPE;
	}
}

abstract class ObjectDTO extends TypedData {
}

class ADTO extends ObjectDTO {
	public static DataType getType() {
		return DataType.A_TYPE;
	}

	ADTO(Document doc) {
	}
}

abstract class Document extends TypedData {
}

class ADocument extends Document {
	public static DataType getType() {
		return DataType.A_TYPE;
	}

	ADocument(ObjectDTO dto) {
	}
}

@FunctionalInterface
interface DocumentFactory {
	Document createDocument(ObjectDTO dto);
}

@FunctionalInterface
interface ObjectDTOFactory {
	ObjectDTO createDocument(Document doc);
}

abstract class AbstractDataFactory {
	public static DocumentFactory getFactory(ObjectDTO dto) {
		switch (dto.getType()) {
			case A_TYPE:
				return ADocument::new;
		}
		return null;
	}

	public static ObjectDTOFactory getFactory(Document doc) {
		switch (doc.getType()) {
			case A_TYPE:
				return ADTO::new;
		}
		return null;
	}
}
