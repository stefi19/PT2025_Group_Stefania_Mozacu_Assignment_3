package BusinessLogicLayer.Validators;
/**
 * Generic interface for validating objects.
 */
public interface Validator<T> {
    /**
     * Validates the given object, depending on the implementation
     * @param t the object to validate
     */
    void validate(T t);
}
