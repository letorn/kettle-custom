package dxservice;

import java.util.ArrayList;
import java.util.List;

import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.trans.step.StepDataInterface;
import org.pentaho.di.trans.step.StepMetaInterface;

public class TestProcess {

	private List<Integer> list = new ArrayList<Integer>();
	private int size = 0;
	private int currIndex = -1;

	public boolean processRow(StepMetaInterface smi, StepDataInterface sdi) throws KettleException {
		Integer obj = list.get(++currIndex);
		String name = "letorn" + obj.toString();
		return true;
	}

	public boolean init(StepMetaInterface stepMetaInterface, StepDataInterface stepDataInterface) {
		for (int i = 0; i < 10; i++)
			list.add(i);
		size = list.size();
		return true;
	}
}
